package com.xiaba2.core;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xiaba2.cms.domain.Member;
import com.xiaba2.util.SessionUtil;

/**
 * Admin 权限验证 验证Member是否存在session中，或cookie中，不存在就跳member/admin/login
 * 
 * @author goddie
 *
 */
public class AdminInterceptor extends HandlerInterceptorAdapter {

	private final Logger log = Logger.getLogger(AdminInterceptor.class
			.getName());

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		log.info("==============执行顺序: 1、preHandle================");
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());

		log.info("requestUri:" + requestUri);
		log.info("contextPath:" + contextPath);
		log.info("url:" + url);

		Member member = (Member) request.getSession().getAttribute("member");

		if (member == null) {

			member = SessionUtil.getInstance().getMemberFromCookie(request);

			if (member != null) {
				return true;
			}

			log.info("Interceptor：跳转到login页面！");
			request.getRequestDispatcher("/member/login").forward(request,
					response);
			return false;

		}
		return true;
	}

}
