package com.xiaba2.invest.controller;

import java.text.SimpleDateFormat;
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
import com.xiaba2.invest.domain.TradeRecord;
import com.xiaba2.invest.domain.User;
import com.xiaba2.invest.service.CashOutService;
import com.xiaba2.invest.service.TradeRecordService;
import com.xiaba2.invest.service.UserService;

@RestController
@RequestMapping("/cashout")
public class CashOutController {
	@Resource
	private CashOutService cashOutService;

	@Resource
	private UserService userService;
	
	@Resource
	private TradeRecordService tradeRecordService;
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
		
		if (user.getWealth()<total) {
			rs.setMsg("余额不足，提现失败");
			return rs;
		}
		
		CashOut entity =new CashOut();
		
		entity.setTotal(total);
		entity.setUser(user);
		entity.setCreatedDate(new Date());
		
		cashOutService.save(entity);
		
		user.setWealth(user.getWealth()-total);
		userService.saveOrUpdate(user);
		
		
		//提现操作
		SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddHHmmss");
		
		String tradeNo = "cash"+sdf.format(new Date()) + user.getId().toString().replace("-", "");
		
		TradeRecord tentity = new TradeRecord();
		tentity.setUser(user);
		tentity.setAmount(total);
		tentity.setCreatedDate(new Date());
		tentity.setTradeNo(tradeNo);
		tentity.setType(3);
		tentity.setOptName("提现");

		tradeRecordService.save(tentity);
		
		
		rs.setMsg("提现申请成功，请耐心等待。");
		rs.setCode(JsonResult.SUCCESS);
		return rs;
		
	}
}