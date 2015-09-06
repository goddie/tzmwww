package com.xiaba2.invest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;
import com.xiaba2.invest.domain.User;
import com.xiaba2.invest.domain.UserContact;
import com.xiaba2.invest.service.UserContactService;
import com.xiaba2.invest.service.UserService;

@Controller
@RequestMapping("/usercontact")
public class UserContactController {
	@Resource
	private UserContactService userContactService;

	@Resource
	private UserService userService;
	/**
	 * 获取推荐列表
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/json/savecontact")
	public JsonResult jsonSaveContact(List<ContackBook> list) {

		JsonResult rs = new JsonResult();

		User user =userService.get(UUID.fromString(list.get(0).uid));
		
		List<UserContact> list2 = new ArrayList<UserContact>();
		
		for (ContackBook contackBook : list) {
			
			UserContact entity =new UserContact();
			entity.setName(contackBook.name);
			entity.setPhone(contackBook.phone);
			entity.setUser(user);
			
			list2.add(entity);
		}
		
		userContactService.saveOrUpdate(list2);
		
		
		//Logger.getGlobal().log(Level.INFO,list.toString());
		
		
		rs.setCode(JsonResult.SUCCESS);
		
		
		return rs;
	}
	
	public static class ContackBook
	{
		private String name;
		private String phone;
		private String uid;
		
		
		public String getUid() {
			return uid;
		}
		public void setUid(String uid) {
			this.uid = uid;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		
	}
}