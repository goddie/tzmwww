package com.xiaba2.invest.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;
import com.xiaba2.invest.domain.PopularApply;
import com.xiaba2.invest.domain.TradeRecord;
import com.xiaba2.invest.domain.User;
import com.xiaba2.invest.service.PopularApplyService;
import com.xiaba2.invest.service.UserService;
import com.xiaba2.util.HttpUtil;

@RestController
@RequestMapping("/popularapply")
public class PopularApplyController {
	@Resource
	private PopularApplyService popularApplyService;

	@Resource
	private UserService userService;
	
	
	@RequestMapping(value = "/admin/list")
	public ModelAndView adminList(@RequestParam("p") int p,HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("admin_popularapply_list");
		
		Page<PopularApply> page = new Page<PopularApply>();
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = popularApplyService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		
  

		//HttpUtil.addSearchLike(criteria, mv, request, "username");	
		page = popularApplyService.findPageByCriteria(criteria, page);

		mv.addObject("list", page.getResult());
		mv.addObject("pageHtml",page.genPageHtml(request));

		return mv;
	}

	@RequestMapping(value = "/json/add")
	public JsonResult jsonAdd(@RequestParam("uid") String uid) {

		User user = userService.getByMemberUUID(uid);

		
		PopularApply entity = new PopularApply();
		entity.setUser(user);
		entity.setCreatedDate(new Date());
		popularApplyService.save(entity);

		JsonResult rs = new JsonResult();
		rs.setCode(JsonResult.SUCCESS);
		rs.setMsg("提交申请成功!");

		return rs;
	}
}