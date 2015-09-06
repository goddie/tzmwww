package com.xiaba2.invest.controller;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;
import com.xiaba2.invest.domain.TradeRecord;
import com.xiaba2.invest.domain.User;
import com.xiaba2.invest.domain.UserProduct;
import com.xiaba2.invest.service.ProductService;
import com.xiaba2.invest.service.UserProductService;
import com.xiaba2.invest.service.UserService;

@RestController
@RequestMapping("/userproduct")
public class UserProductController {
	@Resource
	private UserProductService userProductService;
	

	@Resource
	private UserService userService;

	@Resource
	private ProductService productService;
	/**
	 * 我的已有基金
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/json/mylist")
	public JsonResult jsonMyList(@RequestParam("p") int p,
			@RequestParam("uid") UUID uid, HttpServletRequest request) {

		JsonResult rs=new JsonResult();
		
		User u = userService.get(uid);

		Page<UserProduct> page = new Page<UserProduct>();
		page.setPageNo(p);
		page.setPageSize(15);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = userProductService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("user", u));

		page = userProductService.findPageByCriteria(criteria, page);

		if (page.getResult().isEmpty()) {
			return rs;
		}

		

		rs.setCode(JsonResult.SUCCESS);
		rs.setData(page.getResult());

		return rs;
	}

}