package com.xiaba2.invest.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaba2.cms.controller.AlbumController;
import com.xiaba2.cms.domain.Member;
import com.xiaba2.cms.service.MemberService;
import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;
import com.xiaba2.invest.domain.User;
import com.xiaba2.invest.service.UserService;
import com.xiaba2.util.HttpUtil;
import com.xiaba2.util.SendSMS;

@RestController
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;

	@Resource
	private MemberService memberService;

	/**
	 * 登录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/{name}")
	public ModelAndView getPage(@PathVariable String name) {
		return new ModelAndView("admin_user_" + name);
	}

	/**
	 * 添加页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/action/add")
	public ModelAndView add(User entity, HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("redirect:/user/admin/add");
		// Member member = new Member();
		// member.setUsername(request.getParameter("username"));
		// member.setPassword(request.getParameter("password"));
		// member.setCreatedDate(new Date());
		// memberService.save(member);
		//

		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("username", entity.getUsername()));
		List<User> list = userService.findByCriteria(criteria);
		if (list != null && list.size() > 0) {

			return mv;

		}

		entity.setCreatedDate(new Date());
		// entity.setMember(member);
		// entity.setId(u.getId());
		userService.saveOrUpdate(entity);

		return mv;

	}

	@RequestMapping(value = "/admin/sel")
	public ModelAndView adminList() {
		ModelAndView mv = new ModelAndView("admin_user_sel");

		Page<User> page = new Page<User>();
		page.setPageSize(999);
		page.setPageNo(1);

		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));

		page = userService.findPageByCriteria(criteria, page);

		mv.addObject("list", page.getResult());

		return mv;
	}

	/**
	 * 获取列表
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/json/list")
	public JsonResult jsonList(@RequestParam("p") int p) {

		JsonResult rs = new JsonResult();

		p = Math.max(p, 1);

		Page<User> page = new Page<User>();
		page.setPageNo(p);
		page.setPageSize(15);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		page = userService.findPageByCriteria(criteria, page);

		rs.setCode(JsonResult.SUCCESS);
		rs.setData(page.getResult());

		return rs;
	}
	
	
	@RequestMapping(value = "/json/sel")
	public ModelAndView jsonSel(HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("admin_user_sel");

		int p=1;
		String pstr = request.getParameter("p");
		if(!StringUtils.isEmpty(pstr))
		{
			
			p = Integer.parseInt(pstr);
		}
 

		Page<User> page = new Page<User>();
		page.setPageNo(p);
		page.setPageSize(15);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		page = userService.findPageByCriteria(criteria, page);
 
		mv.addObject("list",page.getResult());
		return mv;
	}
	
	
	/**
	 * 获取推荐列表
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/json/recommlist")
	public JsonResult jsonRecommList(@RequestParam("p") int p) {

		JsonResult rs = new JsonResult();

		p = Math.max(p, 1);

		Page<User> page = new Page<User>();
		page.setPageNo(p);
		page.setPageSize(15);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		page = userService.findPageByCriteria(criteria, page);

		rs.setCode(JsonResult.SUCCESS);
		rs.setData(page.getResult());

		return rs;
	}

	/**
	 * 注册
	 * 
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/json/reg")
	public JsonResult jsonReg(User entity, HttpServletRequest request) {

		JsonResult rs = new JsonResult();

		if (StringUtils.isEmpty(entity.getUsername())) {

			rs.setCode(JsonResult.FAIL);
			rs.setMsg("注册失败");
			return rs;

		}

		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("username", entity.getUsername()));
		List<User> list = userService.findByCriteria(criteria);
		if (list != null && list.size() > 0) {

			rs.setMsg("注册失败");
			return rs;
		}

		entity.setNickname(entity.getUsername());
		entity.setEmail(entity.getUsername());
		entity.setRegIp(HttpUtil.getIpAddr(request));
		entity.setCreatedDate(new Date());

		userService.save(entity);

		if (entity.getId() == null) {

			rs.setMsg("注册失败");
			return rs;
		}

		rs.setCode(JsonResult.SUCCESS);
		rs.setData(entity);
		rs.setMsg("注册成功!");

		return rs;
	}

	/**
	 * 登录
	 * 
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/json/login")
	public JsonResult jsonLogin(User entity, HttpServletRequest request) {

		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("username", entity.getUsername()));
		criteria.add(Restrictions.eq("password", entity.getPassword()));

		List<User> list = userService.findByCriteria(criteria);

		if (!list.isEmpty()) {

			User user = list.get(0);

			JsonResult rs = new JsonResult();
			rs.setCode(JsonResult.SUCCESS);
			rs.setData(user);
			rs.setMsg("登录成功!");

			return rs;

		}

		return new JsonResult("登录失败!");
	}

	/**
	 * 具体玩家信息
	 * 
	 * @param uuid
	 * @return
	 */
	@RequestMapping(value = "/json/detail")
	public JsonResult jsonDetail(HttpServletRequest request) {

		String uid = request.getParameter("uid");

		User u = null;

		if (!StringUtils.isEmpty(uid)) {
			UUID id = UUID.fromString(uid);
			u = userService.get(id);
		}

		if (u == null) {
			return new JsonResult();
		}

		JsonResult rs = new JsonResult();

		rs.setCode(JsonResult.SUCCESS);
		rs.setData(u);

		return rs;
	}

	/**
	 * 推荐红人
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/json/advice")
	public JsonResult jsonAdvice() {

		List<User> list = userService.loadAll();

		Random ra = new Random();

		int idx = ra.nextInt(list.size() - 1);

		User u = list.get(idx);

		if (u == null) {
			return new JsonResult();
		}

		JsonResult rs = new JsonResult();

		rs.setCode(JsonResult.SUCCESS);
		rs.setData(list.get(idx));

		return rs;
	}

	/**
	 * 发送短信
	 * 
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "/json/regsms")
	public JsonResult jsonRegSms(@RequestParam("phone") String phone) {
		JsonResult rs = new JsonResult();
		// rs.setCode(JsonResult.SUCCESS);
		// return rs;
		
		
		
		

		Pattern p = null;
		Matcher m = null;

		p = Pattern.compile("^[1][0-9]{10}$"); // 验证手机号
		m = p.matcher(phone);

		if (!m.matches()) {
			rs.setMsg("手机号不正确");
			return rs;
		}
		
		
		
		//是否已注册
		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("phone", phone));

		List<User> list = userService.findByCriteria(criteria);

		if (!list.isEmpty()) {
			rs.setMsg("手机号已被注册。");
			return rs;
		}

		StringBuilder sb = new StringBuilder();
		sb.append(SendSMS.getRand());
		sb.append(SendSMS.getRand());
		sb.append(SendSMS.getRand());
		sb.append(SendSMS.getRand());
		sb.append(SendSMS.getRand());
		sb.append(SendSMS.getRand());

		String mess = "您的验证码是：" + sb.toString()
				+ "。请不要把验证码泄露给其他人。如非本人操作，可不用理会！";
		// String mess = "您的校验码是：" + sb.toString()+
		// "。请不要把校验码泄露给其他人。如非本人操作，可不用理会！";

		// String code = "2";
		// String code = SendSMS.sendSMS("cf_shzywh", "cfshzywh", phone, mess);

		String code = SendSMS.sendSMS("cf_itzmao", "itzmao123", phone, mess);
		if (code.endsWith("2")) {

			// Member member = new Member();
			//
			// member.setUsername(phone);
			// member.setCreatedDate(new Date());
			// member = memberService.save(member);
			//
			// User user = member.getUser();

			User user = new User();
			user.setNickname("投资猫网友");
			user.setPhone(phone);
			user.setUsername(phone);
			user.setCreatedDate(new Date());
			user.setLastModifiedBy(sb.toString());

			userService.saveOrUpdate(user);

		}

		rs.setCode(JsonResult.SUCCESS);

		return rs;
	}

	/**
	 * 完成密码
	 * 
	 * @param password
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "/json/regpassword")
	public JsonResult jsonRegPassword(
			@RequestParam("password") String password,
			@RequestParam("phone") String phone,
			@RequestParam("code") String code) {

		JsonResult rs = new JsonResult();
		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("phone", phone));
		criteria.add(Restrictions.eq("lastModifiedBy", code));

		List<User> list = userService.findByCriteria(criteria);

		if (list == null || list.size() == 0) {
			rs.setMsg("验证码不正确");
			return rs;
		}

		User user = list.get(0);

		user.setPassword(password);
		user.setLastModifiedBy("");

		// user.getMember().setPassword(password);

		userService.saveOrUpdate(user);
		// memberService.saveOrUpdate(user.getMember());

		rs.setData(user);
		rs.setCode(JsonResult.SUCCESS);
		rs.setMsg("注册成功");
		return rs;
	}

	/**
	 * 获取列表
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/json/search")
	public JsonResult jsonSearch(@RequestParam("s") String s) {

		Page<User> page = new Page<User>();
		page.setPageNo(1);
		page.setPageSize(999);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.like("nickname", s, MatchMode.ANYWHERE));
		page = userService.findPageByCriteria(criteria, page);

		JsonResult rs = new JsonResult();

		rs.setCode(JsonResult.SUCCESS);
		rs.setData(page.getResult());

		return rs;
	}

	/**
	 * 上传头像
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/json/upavatar")
	public JsonResult jsonUpavatar(
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request) {

		JsonResult rs = new JsonResult();

		String uid = request.getParameter("uid");
		if (StringUtils.isEmpty(uid)) {
			return rs;
		}

		// String path = System.getProperty("myapp.root") + "upload";
		String path = this.getClass().getClassLoader().getResource("/")
				.getPath();
		path = path.replace("WEB-INF" + File.separator + "classes"
				+ File.separator, "upload");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String pdate = sdf.format(new Date());
		path = path + File.separator + pdate;
		String ext = file.getOriginalFilename().substring(
				file.getOriginalFilename().lastIndexOf("."));
		String fileName = UUID.randomUUID().toString().replace("-", "") + ext;
		// String fileName = new Date().getTime()+".jpg";
		Logger.getLogger(AlbumController.class.toString())
				.log(Level.INFO, path);
		// System.out.println(path);
		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String extPath = request.getContextPath() + "/upload/" + pdate + "/";
		
		User user = userService.get(UUID.fromString(uid));
		
		
		user.setAvatar(extPath+fileName);
		
		userService.saveOrUpdate(user);
		
		rs.setCode(JsonResult.SUCCESS);
		rs.setData(user);
		
		return rs;
	}
	
	
	/**
	 * 修改密码
	 * @param uid
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/json/uppassword")
	public JsonResult jsonUpPassword(@RequestParam("uid") UUID uid, @RequestParam("password") String password) {
		JsonResult rs = new JsonResult();
		
		User user = userService.get(uid);
		
		user.setPassword(password);
		 
		userService.saveOrUpdate(user);
		
		rs.setCode(JsonResult.SUCCESS);
		rs.setData(user);
		rs.setMsg("修改成功!");
	
		return rs;
	}

	
	/**
	 * 修改个人信息
	 * @param uid
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/json/update")
	public JsonResult jsonUpdate(@RequestParam("uid") UUID uid,HttpServletRequest request) {
		JsonResult rs = new JsonResult();
		
		User user = userService.get(uid);
		
		user.setNickname(request.getParameter("nickname"));
		user.setPhone(request.getParameter("phone"));
		user.setInfo(request.getParameter("info"));
		
		
		userService.saveOrUpdate(user);
		
		rs.setCode(JsonResult.SUCCESS);
		rs.setData(user);
		rs.setMsg("修改成功!");
	
		return rs;
	}
	
	
	/**
	 * 修改密码
	 * @param uid
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/json/updatenickname")
	public JsonResult jsonUpdateNickname(@RequestParam("uid") UUID uid,HttpServletRequest request) {
		JsonResult rs = new JsonResult();
		
		User user = userService.get(uid);
		
		user.setNickname(request.getParameter("nickname"));
		
		userService.saveOrUpdate(user);
		
		rs.setCode(JsonResult.SUCCESS);
		rs.setData(user);
		rs.setMsg("修改成功!");
	
		return rs;
	}
}