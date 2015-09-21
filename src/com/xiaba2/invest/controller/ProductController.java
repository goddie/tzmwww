package com.xiaba2.invest.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;
import com.xiaba2.invest.domain.KeyValue;
import com.xiaba2.invest.domain.Product;
import com.xiaba2.invest.domain.ProductInfo;
import com.xiaba2.invest.domain.TradeRecord;
import com.xiaba2.invest.domain.User;
import com.xiaba2.invest.service.IncomeRecordService;
import com.xiaba2.invest.service.ProductInfoService;
import com.xiaba2.invest.service.ProductService;
import com.xiaba2.invest.service.TradeRecordService;
import com.xiaba2.invest.service.UserService;
import com.xiaba2.invest.vo.ProductVO;
import com.xiaba2.util.HttpUtil;
import com.xiaba2.util.ImportUtil;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Resource
	private ProductService productService;

	@Resource
	private UserService userService;

	@Resource
	private TradeRecordService tradeRecordService;

	@Resource
	private ProductInfoService productInfoService;
	
	@Resource
	private IncomeRecordService incomeRecordService;

	
	
	
	
	
	/**
	 * 新增产品
	 * @return
	 */
	@RequestMapping(value = "/admin/add")
	public ModelAndView adminAdd() {
		ModelAndView mv = new ModelAndView("admin_product_add");
 
		return mv;
	}
	
	/**
	 * 新增产品
	 * @return
	 */
	@RequestMapping(value = "/admin/edit")
	public ModelAndView adminEdit(@RequestParam("id") UUID pid) {
		ModelAndView mv = new ModelAndView("admin_product_edit");
 
		Product entity = productService.get(pid);
		mv.addObject("entity", entity);
		
		return mv;
	}
	
	
	/**
	 * 删除操作
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/action/del")
	public ModelAndView actionDel(@RequestParam("id") UUID uid) {

		ModelAndView mv = new ModelAndView("redirect:/product/admin/list?p=1");
		
		Product u = productService.get(uid);
		
		u.setIsDelete(1);
		
		productService.saveOrUpdate(u);
		

		return mv;
	}

	
	/**
	 * 新增产品
	 * @return
	 */
	@RequestMapping(value = "/action/add")
	public ModelAndView actionAdd(Product entity) {
		ModelAndView mv = new ModelAndView("redirect:/product/admin/list?p=1");
		
		entity.setCreatedDate(new Date());
		
		productService.save(entity);
		
		
		return mv;
	}
	
	
	
	/**
	 * 导入产品数据
	 * 
	 * @return
	 */

	@RequestMapping(value = "/action/import")
	public ModelAndView actionImport() {
		ModelAndView mv = new ModelAndView("redirect:/product/admin/import");
		List<Product> list = ImportUtil.loadProduct();
		for (Product product : list) {
			productService.save(product);
		}
		return mv;
	}

	/**
	 * 购买须知
	 * 
	 * @param pid
	 * @param type
	 *            1=购买须知 2=产品详情
	 * @return
	 */
	@RequestMapping(value = "/page/notice")
	public ModelAndView getNotice(@RequestParam("pid") String pid,
			@RequestParam("type") int type) {

		ModelAndView mv = new ModelAndView("product_notice");

		UUID id = UUID.fromString(pid);

		Product entity = productService.get(id);

		if (entity == null) {
			return mv;
		}

		DetachedCriteria criteria = productInfoService.createDetachedCriteria();
		criteria.add(Restrictions.eq("type", type));
		criteria.add(Restrictions.eq("product", entity));

		List<ProductInfo> list = productInfoService.findByCriteria(criteria);

		mv.addObject("list", list);

		return mv;
	}

	@RequestMapping(value = "/admin/list")
	public ModelAndView getList(@RequestParam("p") int p, HttpServletRequest request) {

//		List<Product> list = productService.loadAll();

		ModelAndView mv = new ModelAndView("admin_product_list");
		
		Page<Product> page = new Page<Product>();
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = productService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		
		
		HttpUtil.addSearchLike(criteria, mv, request, "CPMC");
//		HttpUtil.addSearchEq(criteria, mv, request, "CPBH");
		String value = request.getParameter("CPBH");
		if(!StringUtils.isEmpty(value))
		{
			criteria.add(Restrictions.eq("CPBH", Integer.parseInt(value)));
			mv.addObject("CPBH",value);
		}
		
 
 
		page = productService.findPageByCriteria(criteria, page);

		mv.addObject("list", page.getResult());
		mv.addObject("pageHtml",page.genPageHtml(request));
		
		return mv;
	}

//	@RequestMapping(value = "/add")
//	public ModelAndView add(Product entity) {
//		entity.setCreatedDate(new Date());
//
//		productService.save(entity);
//
//		return new ModelAndView("redirect:/product/admin/add");
//	}

	/**
	 * 获取列表
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/json/list")
	public JsonResult jsonList(@RequestParam("type") int type,
			@RequestParam("p") int p) {
		p = Math.max(p, 1);

		Page<Product> page = new Page<Product>();
		page.setPageNo(p);
		page.setPageSize(15);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = productService.createDetachedCriteria();

		// 推荐
		if (type == 0) {
			criteria.add(Restrictions.eq("recom", 1));
		}

		// 低风险
		if (type == 1) {
			criteria.add(Restrictions.eq("FXDJ", 1));
		}

		// 高风险
		if (type == 2) {
			criteria.add(Restrictions.eq("FXDJ", 2));
		}

		page = productService.findPageByCriteria(criteria, page);

		List<ProductVO> list = new ArrayList<ProductVO>();
		
		try {
			for (Product product : page.getResult()) {

				ProductVO vo = new ProductVO();

				BeanUtils.copyProperties(vo, product);
				
				List<User> list2 = incomeRecordService.getTopUser(6);
				
				vo.setList(list2);

				list.add(vo);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JsonResult rs = new JsonResult();

		rs.setCode(JsonResult.SUCCESS);
		rs.setData(list);

		return rs;
	}
	
	
	
	
	/**
	 * 获取列表
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/json/sel")
	public ModelAndView jsonSel(HttpServletRequest request) {
		
		ModelAndView mv =new ModelAndView("admin_product_sel");
		int p=1;
		String pstr = request.getParameter("p");
		if(!StringUtils.isEmpty(pstr))
		{
			p = Integer.parseInt(pstr);
		}
		Page<Product> page = new Page<Product>();
		page.setPageNo(p);
		page.setPageSize(15);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = productService.createDetachedCriteria();
 
		page = productService.findPageByCriteria(criteria, page);

		mv.addObject("list",page.getResult());

		return mv;
	}

	/**
	 * 具体产品信息
	 * 
	 * @param uuid
	 * @return
	 */
	@RequestMapping(value = "/json/detail")
	public JsonResult jsonDetail(@RequestParam("uuid") String uuid) {

		UUID id = UUID.fromString(uuid);

		Product entity = productService.get(id);

		if (entity == null) {
			return new JsonResult();
		}

		JsonResult rs = new JsonResult();
		rs.setCode(JsonResult.SUCCESS);
		rs.setData(entity);

		return rs;
	}

	/**
	 * 获取列表
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/json/listuser")
	public JsonResult jsonListUser(@RequestParam("uuid") String uuid,
			@RequestParam("p") int p) {

		User user = userService.getByMemberUUID(uuid);

		p = Math.max(p, 1);

		Page<TradeRecord> page = new Page<TradeRecord>();
		page.setPageNo(p);
		page.setPageSize(10);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = tradeRecordService.createDetachedCriteria();
		criteria.add(Restrictions.eq("user.id", user.getId()));

		page = tradeRecordService.findPageByCriteria(criteria, page);

		List<Product> list = new ArrayList<Product>();

		for (TradeRecord record : page.getResult()) {
			list.add(record.getProduct());
		}

		JsonResult rs = new JsonResult();

		rs.setCode(JsonResult.SUCCESS);
		rs.setData(list);

		return rs;
	}

}