package com.xiaba2.invest.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.invest.domain.IncomeRecord;
import com.xiaba2.invest.domain.Product;
import com.xiaba2.invest.domain.User;
import com.xiaba2.invest.service.IncomeRecordService;
import com.xiaba2.invest.service.ProductService;
import com.xiaba2.invest.service.UserService;
import com.xiaba2.util.ImportUtil;

@Controller
@RequestMapping("/incomerecord")
public class IncomeRecordController {
	@Resource
	private IncomeRecordService incomeRecordService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private ProductService productService;
	
	
	@RequestMapping(value = "/admin/add")
	public ModelAndView adminAdd() {
		ModelAndView mv = new ModelAndView("admin_incomerecord_add");
		return mv;
	}
	
	@RequestMapping(value = "/action/add")
	public ModelAndView actionAdd(IncomeRecord entity,@RequestParam("uid") UUID uid,@RequestParam("pid") UUID pid) {
		ModelAndView mv = new ModelAndView("redirect:/incomerecord/admin/add");
		
		User user = userService.get(uid);
		
		Product product = productService.get(pid);
		
		entity.setUser(user);
		entity.setProduct(product);
		entity.setCreatedDate(new Date());
		
		incomeRecordService.save(entity);
		
		return mv;
	}
	
	
	
}