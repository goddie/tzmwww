package com.xiaba2.invest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpRequest;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;
import com.xiaba2.invest.domain.Product;
import com.xiaba2.invest.domain.TradeRecord;
import com.xiaba2.invest.domain.User;
import com.xiaba2.invest.service.ProductService;
import com.xiaba2.invest.service.TradeRecordService;
import com.xiaba2.invest.service.UserService;
import com.xiaba2.util.HttpUtil;

@RestController
@RequestMapping("/traderecord")
public class TradeRecordController {
	@Resource
	private TradeRecordService tradeRecordService;

	@Resource
	private UserService userService;

	@Resource
	private ProductService productService;

	 
	@RequestMapping(value = "/admin/list")
	public ModelAndView adminList(@RequestParam("p") int p,HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("admin_traderecord_list");
		
		Page<TradeRecord> page = new Page<TradeRecord>();
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = tradeRecordService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		
		
		String typeStr =request.getParameter("type");
		if(!StringUtils.isEmpty(typeStr))
		{
			int type  = Integer.parseInt(typeStr);
			criteria.add(Restrictions.eq("type", type));
		}

		//HttpUtil.addSearchLike(criteria, mv, request, "username");	
		page = tradeRecordService.findPageByCriteria(criteria, page);

		mv.addObject("list", page.getResult());
		mv.addObject("pageHtml",page.genPageHtml(request));

		return mv;
	}
	
	
	/**
	 * 删除
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/action/del")
	public ModelAndView actionDel(@RequestParam("id") UUID uid) {

		ModelAndView mv = new ModelAndView("redirect:/traderecord/admin/list");
		
		TradeRecord u = tradeRecordService.get(uid);
		
		u.setIsDelete(1);
		
		tradeRecordService.saveOrUpdate(u);
		

		return mv;
	}
	
	/**
	 * 充值
	 * 
	 * @param uid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/json/addcharge")
	public JsonResult jsonAddCharge(@RequestParam("uid") UUID uid,
			HttpServletRequest request) {
		JsonResult rs = new JsonResult();

		User user = userService.get(uid);

		float amount = Float.parseFloat(request.getParameter("amount"));
		if (amount <= 0) {
			return rs;
		}

		//充值
		TradeRecord entity = new TradeRecord();
		entity.setUser(user);
		entity.setAmount(amount);
		entity.setCreatedDate(new Date());
		entity.setTradeNo(request.getParameter("tradeNo").replace("-", ""));
		entity.setType(2);
		entity.setOptName("充值");

		tradeRecordService.save(entity);

		rs.setCode(JsonResult.SUCCESS);
		rs.setMsg("充值成功!");

		return rs;
	}

	@RequestMapping(value = "/json/add")
	public JsonResult jsonAdd(@RequestParam("uid") String uid,
			@RequestParam("pid") String pid, TradeRecord entity,
			HttpServletRequest request) {
		if (StringUtils.isEmpty(uid) || StringUtils.isEmpty(pid)) {
			return new JsonResult("购买失败!");
		}

		String fid = request.getParameter("fid");
		if (!StringUtils.isEmpty(fid)) {
			User follow = userService.get(UUID.fromString(fid));
			entity.setFollow(follow);
		}

		User user = userService.get(UUID.fromString(uid));

		UUID productId = UUID.fromString(pid);

		Product product = productService.get(productId);

		entity.setUser(user);
		entity.setProduct(product);
		entity.setCreatedDate(new Date());
		entity.setTitle(product.getCPMC());
		entity.setType(1);
		entity.setOptName("买入");

		tradeRecordService.save(entity);

		if (entity.getType() == 1) {

			// 增加猫币

			user.setScore(user.getScore() + Math.abs(entity.getAmount()));
			userService.saveOrUpdate(user);

		}

		JsonResult rs = new JsonResult();
		rs.setCode(JsonResult.SUCCESS);
		rs.setMsg("购买成功!");
		rs.setData(entity);

		return rs;
	}

	/**
	 * 获取列表
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/json/list")
	public JsonResult jsonList(@RequestParam("p") int p,
			HttpServletRequest request) {

		JsonResult rs = new JsonResult();

		String uid = request.getParameter("uid");

		User u = null;

		if (!StringUtils.isEmpty(uid)) {
			UUID id = UUID.fromString(uid);
			u = userService.get(id);
		}

		if (u == null) {
			return new JsonResult("没有记录");
		}

		p = Math.max(p, 1);

		Page<TradeRecord> page = new Page<TradeRecord>();
		page.setPageNo(p);
		page.setPageSize(15);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = tradeRecordService.createDetachedCriteria();

		criteria.add(Restrictions.eq("user.id", u.getId()));

		page = tradeRecordService.findPageByCriteria(criteria, page);

		if (page.getResult().isEmpty()) {
			return new JsonResult("没有记录");
		}

		rs.setCode(JsonResult.SUCCESS);
		rs.setData(page.getResult());

		return rs;
	}

	/**
	 * 我的已有基金
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/json/mylist")
	public JsonResult jsonMyList(@RequestParam("p") int p,
			@RequestParam("uid") String uid, HttpServletRequest request) {

		User u = userService.get(UUID.fromString(uid));

		p = Math.max(p, 1);

		Page<TradeRecord> page = new Page<TradeRecord>();
		page.setPageNo(p);
		page.setPageSize(10);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = tradeRecordService.createDetachedCriteria();
		criteria.add(Restrictions.eq("user.id", u.getId()));

		page = tradeRecordService.findPageByCriteria(criteria, page);

		if (page.getResult().isEmpty()) {
			return new JsonResult("没有记录");
		}

		for (TradeRecord tradeRecord : page.getResult()) {
			DetachedCriteria criteria2 = tradeRecordService
					.createDetachedCriteria();
			criteria2.add(Restrictions.eq("follow.id", u.getId()));
			criteria2.add(Restrictions.eq("product.id", tradeRecord
					.getProduct().getId()));

			List<TradeRecord> list2 = tradeRecordService
					.findByCriteria(criteria2);
			if (list2 != null && list2.size() > 0) {
				tradeRecord.setHasFans(1);
			}

		}

		JsonResult rs = new JsonResult();

		rs.setCode(JsonResult.SUCCESS);
		rs.setData(page.getResult());

		return rs;
	}

	/**
	 * 跟我的
	 * 
	 * @param p
	 * @param uid
	 * @param pid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/json/fanlist")
	public JsonResult jsonFanList(@RequestParam("p") int p,
			@RequestParam("uid") String uid, @RequestParam("pid") String pid,
			HttpServletRequest request) {

		User u = userService.get(UUID.fromString(uid));

		p = Math.max(p, 1);

		Page<TradeRecord> page = new Page<TradeRecord>();
		page.setPageNo(p);
		page.setPageSize(10);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = tradeRecordService.createDetachedCriteria();
		criteria.add(Restrictions.eq("follow.id", u.getId()));
		criteria.add(Restrictions.eq("product.id", UUID.fromString(pid)));

		page = tradeRecordService.findPageByCriteria(criteria, page);

		if (page.getResult().isEmpty()) {
			return new JsonResult("没有记录");
		}

		JsonResult rs = new JsonResult();

		rs.setCode(JsonResult.SUCCESS);
		rs.setData(page.getResult());

		return rs;
	}

	/**
	 * 是否公开
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/json/isopen")
	public JsonResult jsonIsOpen(@RequestParam("tid") String tid,
			@RequestParam("isopen") int isopen, HttpServletRequest request) {
		JsonResult rs = new JsonResult();

		TradeRecord tr = tradeRecordService.get(UUID.fromString(tid));

		tr.setIsShow(isopen);

		tradeRecordService.saveOrUpdate(tr);

		rs.setCode(JsonResult.SUCCESS);
		rs.setData(tr);

		return rs;
	}

}