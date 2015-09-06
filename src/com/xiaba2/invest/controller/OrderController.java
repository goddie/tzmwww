package com.xiaba2.invest.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.core.JsonResult;
import com.xiaba2.invest.domain.Order;
import com.xiaba2.invest.domain.Product;
import com.xiaba2.invest.domain.User;
import com.xiaba2.invest.service.OrderService;
import com.xiaba2.invest.service.ProductService;
import com.xiaba2.invest.service.UserService;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Resource
	private OrderService orderService;

	@Resource
	private ProductService productService;
	
	@Resource
	private UserService userService;
	/**
	 * 发送消息
	 * 
	 * @param from
	 * @param sendTo
	 * @param content
	 */
	@RequestMapping(value = "/notice/alipay")
	public String noticeAlipay(HttpServletRequest request) {
		String msg = "success";

		return msg;
	}
	
	
	/**
	 * 手机创建订单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/json/add")
	public JsonResult jsonAdd(Order entity,@RequestParam("uid") UUID uid,@RequestParam("pid") UUID pid, HttpServletRequest request) {
		JsonResult rs = new JsonResult();
		
		orderService.save(entity);
		
		Product product  = productService.get(pid);
		
		User user = userService.get(uid);
		
		
		SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMdd");
		String d=sdf.format(new Date());
		
		String tradeNo = d+entity.getId().toString().replace("-", "");
		
		entity.setProduct(product);
		entity.setUser(user);
		entity.setTradeNo(tradeNo);
		entity.setCreatedDate(new Date());

		
		orderService.saveOrUpdate(entity);
		
		rs.setData(entity);
		rs.setCode(JsonResult.SUCCESS);
		
		return rs;
	}
}