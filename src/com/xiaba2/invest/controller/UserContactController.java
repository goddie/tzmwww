package com.xiaba2.invest.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;
import com.xiaba2.invest.domain.Follow;
import com.xiaba2.invest.domain.TradeRecord;
import com.xiaba2.invest.domain.User;
import com.xiaba2.invest.domain.UserContact;
import com.xiaba2.invest.service.FollowService;
import com.xiaba2.invest.service.UserContactService;
import com.xiaba2.invest.service.UserService;
import com.xiaba2.util.SessionUtil.MemberCookie;

@RestController
@RequestMapping("/usercontact")
public class UserContactController {
	@Resource
	private UserContactService userContactService;

	@Resource
	private UserService userService;

	@Resource
	private FollowService followService;

	/**
	 * 获取推荐列表
	 * 
	 * @param p
	 * @return
	 */
	// , produces={"application/xml", "application/json"}) @RequestParam("data")
	// List<ContackBook> list,@RequestParam("uid") UUID uid
	@RequestMapping(value = "/json/savecontact")
	public JsonResult jsonSaveContact(HttpServletRequest request) {
		JsonResult rs = new JsonResult();
		
		String listStr = request.getParameter("data");
		if (StringUtils.isEmpty(listStr)) {
			return rs;
		}

		ObjectMapper mapper = new ObjectMapper();

		List<ContackBook> list = new ArrayList<ContackBook>();
		try {
			list = mapper.readValue(listStr,
					new TypeReference<List<ContackBook>>() {
					});
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (list.isEmpty()) {
			return rs;
		}

		// list = request.getParameter("data");
		User user = userService.get(UUID.fromString(list.get(0).uid));
		
		// List<UserContact> list2= new ArrayList<UserContact>();
		for (ContackBook contackBook : list) {

		
			Pattern pattern = Pattern.compile("[^0-9]");
		    Matcher matcher = pattern.matcher(contackBook.phone);
		    String phoneStr = matcher.replaceAll("");

			
//			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
//            Matcher m = p.matcher(phoneStr);
//            phoneStr = m.replaceAll("");
//			phoneStr = phoneStr.replace("-", "").replace("+86", "").replace("(", "").replace(")", "").replaceAll("·","").trim();
		    
		    User contact = userService.getByPhone(phoneStr);
		    //该联系人不是投资猫用户
		    if(contact==null)
		    {
		    	continue;
		    }
 
		    
			UserContact entity = userContactService.getUserContact(user, contact);

			//联系人已存在
			if (entity != null) {
				continue;
			}else
			{
				entity = new UserContact();
			}
			
			entity.setName(contackBook.name);
			entity.setPhone(phoneStr);
			entity.setUser(user);
			//entity.setIsFollow(1);
			entity.setCreatedDate(new Date());

			if (contact != null) {
				entity.setContact(contact);
//				Follow follow = followService.getUserContact(user, contact);
//				if(follow==null)
//				{
//					follow = new Follow();
//					follow.setFrom(user);
//					follow.setSendTo(contact);
//					follow.setCreatedDate(new Date());
//					followService.save(follow);
//				}
			}

			userContactService.save(entity);

			// list2.add(entity);
		}

		// userContactService.saveOrUpdate(list2);

		// Logger.getGlobal().log(Level.INFO,list.toString());

		rs.setCode(JsonResult.SUCCESS);

		return rs;
	}

	
	
	/**
	 * 用户联系人
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/json/delete")
	public JsonResult jsonDelete(@RequestParam("cid") UUID uid) {
		JsonResult rs = new JsonResult();

		UserContact userContact = userContactService.get(uid);
		
		userContactService.deleteContact(userContact);
		//followService.deleteFollow(user, contact);
		
		rs.setCode(JsonResult.SUCCESS);
		
		return rs;
	}
	
	
	
	/**
	 * 获取列表
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/json/search")
	public JsonResult jsonSearch(@RequestParam("s") String s,HttpServletRequest request) {

		Page<UserContact> page = new Page<UserContact>();
		page.setPageNo(1);
		page.setPageSize(999);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = userContactService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.like("name", s, MatchMode.ANYWHERE));
		
		int type = Integer.parseInt(request.getParameter("type"));
		// 小猫推荐
		if (type == 0) {
			
		}

		// 粉丝
		if (type == 1) {
			criteria.add(Restrictions.eq("isFan", 1));
			criteria.add(Restrictions.eq("isFollow", 0));
		}

		// 关注
		if (type == 2) {
			criteria.add(Restrictions.eq("isFan", 0));
			criteria.add(Restrictions.eq("isFollow", 1));
		}
				
		page = userContactService.findPageByCriteria(criteria, page);

		JsonResult rs = new JsonResult();

		rs.setCode(JsonResult.SUCCESS);
		rs.setData(page.getResult());

		return rs;
	}
	
	
	/**
	 * 用户联系人
	 * 
	 * @param uid
	 * @param type
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/json/list")
	public JsonResult jsonList(@RequestParam("uid") UUID uid,
			@RequestParam("type") int type, @RequestParam("p") int p) {

		JsonResult rs = new JsonResult();

		Page<UserContact> page = new Page<UserContact>();
		page.setPageNo(p);
		page.setPageSize(15);
		page.addOrder("createdDate", "desc");

		User user = userService.get(uid);

		DetachedCriteria criteria = userContactService.createDetachedCriteria();

		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("user", user));
		criteria.add(Restrictions.isNotNull("contact"));
		// 小猫推荐
		if (type == 0) {
			
		}

		// 粉丝
		if (type == 1) {
			criteria.add(Restrictions.eq("isFan", 1));
			criteria.add(Restrictions.eq("isFollow", 0));
		}

		// 关注
		if (type == 2) {
			criteria.add(Restrictions.eq("isFan", 0));
			criteria.add(Restrictions.eq("isFollow", 1));
		}

		page = userContactService.findPageByCriteria(criteria, page);

		if (page.getResult().isEmpty()) {
			return new JsonResult("没有记录");
		}

//		List<User> list2 = new ArrayList<User>();
//
//		for (UserContact userContact : page.getResult()) {
//			list2.add(userContact.getContact());
//		}

		rs.setCode(JsonResult.SUCCESS);
		rs.setData(page.getResult());

		return rs;
	}

	public static class ContackBook {
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