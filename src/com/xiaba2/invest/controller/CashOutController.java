package com.xiaba2.invest.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.core.JsonResult;
import com.xiaba2.invest.domain.CashOut;
import com.xiaba2.invest.domain.IncomeRecord;
import com.xiaba2.invest.domain.Product;
import com.xiaba2.invest.domain.User;
import com.xiaba2.invest.service.CashOutService;
import com.xiaba2.invest.service.UserService;

@RestController
@RequestMapping("/cashout")
public class CashOutController {
	@Resource
	private CashOutService cashOutService;

	@Resource
	private UserService userService;
	
	/**
	 * 手机提现申请
	 * @param uid
	 * @param total
	 * @return
	 */
	@RequestMapping(value = "/json/add")
	public JsonResult jsondd(@RequestParam("uid") UUID uid,@RequestParam("total") float total) {
		JsonResult rs = new JsonResult();
		
		User user = userService.get(uid);
		
		
		CashOut entity =new CashOut();
		
		entity.setTotal(total);
		entity.setUser(user);
		entity.setCreatedDate(new Date());
		
		cashOutService.save(entity);
		
		rs.setMsg("提现申请成功，请耐心等待。");
		rs.setCode(JsonResult.SUCCESS);
		return rs;
		
	}
}