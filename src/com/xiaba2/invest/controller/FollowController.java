package com.xiaba2.invest.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;
import com.xiaba2.invest.domain.Follow;
import com.xiaba2.invest.domain.User;
import com.xiaba2.invest.domain.UserContact;
import com.xiaba2.invest.service.FollowService;
import com.xiaba2.invest.service.UserContactService;
import com.xiaba2.invest.service.UserService;

@RestController
@RequestMapping("/follow")
public class FollowController {
	@Resource
	private FollowService followService;

	@Resource
	private UserService userService;

	@Resource
	private UserContactService userContactService;

	/**
	 * 添加关注
	 */
	@RequestMapping(value = "/json/add")
	public JsonResult jsonAdd(@RequestParam("from") String from,
			@RequestParam("sendTo") String sendTo,
			@RequestParam("status") int status) {

		JsonResult rs = new JsonResult();
		
		
		UUID id1 = UUID.fromString(from);
		UUID id2 = UUID.fromString(sendTo);
		User u1 = userService.get(id1);
		User u2 = userService.get(id2);

		Follow follow = followService.getUserContact(u1, u2);

		if (follow != null) {
			followService.delete(follow);
		}

		follow = new Follow();
		follow.setFrom(u1);
		follow.setSendTo(u2);
		follow.setStatus(status);
		follow.setCreatedDate(new Date());
		follow.setIsFollow(1);

		follow.setStatus(status);
		followService.save(follow);

		UserContact userContact = userContactService.getUserContact(u1, u2);
		if(userContact!=null)
		{
			userContact.setIsFollow(1);
			userContactService.saveOrUpdate(userContact);
		}
		
		

		UserContact userContact2 = userContactService.getUserContact(u2, u1);
		if(userContact2!=null)
		{
			userContact.setIsFan(1);
			userContactService.saveOrUpdate(userContact);
		}
		
		Follow follow2 = followService.getUserContact(u2, u1);
		if (follow2 != null) {
			follow.setIsFan(1);
			followService.saveOrUpdate(follow);
		}

		rs.setCode(JsonResult.SUCCESS);
		rs.setMsg("操作成功!");
		
		return rs;
	}
	
	
	/**
	 * 关注列表
	 * @param uid
	 * @param type
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/json/list")
	public JsonResult jsonList(@RequestParam("uid") UUID uid,
			@RequestParam("type") int type,
			@RequestParam("p") int p) {
	
		JsonResult rs= new JsonResult();
		
		User user = userService.get(uid);
		
		Page<Follow> page = new Page<Follow>();
		page.setPageNo(p);
		page.setPageSize(15);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = followService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		
		//我关注
		if(type==1)
		{
			criteria.add(Restrictions.eq("from", user));
		}
		
		//关注我
		if(type==2)
		{
			criteria.add(Restrictions.eq("sendTo", user));
		}
		
		page = followService.findPageByCriteria(criteria, page);
		
		rs.setCode(JsonResult.SUCCESS);
		rs.setData(page.getResult());
		
		return rs;
	}

}