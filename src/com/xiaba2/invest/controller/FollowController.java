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
			follow.setStatus(status);
			followService.saveOrUpdate(follow);
			
			rs.setCode(JsonResult.SUCCESS);

			if (status == 1) {
				rs.setMsg("关注成功！");
			}
			if (status == 0) {
				rs.setMsg("发送成功！");
			}

			return rs;
		}

		follow = new Follow();
		follow.setFrom(u1);
		follow.setSendTo(u2);
		follow.setStatus(status);
		follow.setCreatedDate(new Date());

		followService.save(follow);

		userContactService.updateByFollow(u1, u2);


		rs.setCode(JsonResult.SUCCESS);

		if (status == 1) {
			rs.setMsg("关注成功！");
		}
		if (status == 0) {
			rs.setMsg("发送成功！");
		}

		return rs;
	}

}