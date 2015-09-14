package com.xiaba2.invest.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

 




import com.xiaba2.core.JsonResult;
import com.xiaba2.invest.domain.Order;
import com.xiaba2.invest.domain.Product;
import com.xiaba2.invest.domain.TradeRecord;
import com.xiaba2.invest.domain.User;
import com.xiaba2.invest.domain.UserProduct;
import com.xiaba2.invest.service.OrderService;
import com.xiaba2.invest.service.ProductService;
import com.xiaba2.invest.service.TradeRecordService;
import com.xiaba2.invest.service.UserProductService;
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
	
	@Resource
	private UserProductService userProductService;
	
	@Resource
	private TradeRecordService tradeRecordService;
	
	/**
	 * 支付宝异步通知
	 * 
	 * @param from
	 * @param sendTo
	 * @param content
	 */
	@RequestMapping(value = "/notice/alipay")
	public String noticeAlipay(HttpServletRequest request) {
//		Logger.getLogger("========================");
//		Logger.getLogger(this.getClass().getName()).log(Level.INFO, request.getQueryString());
//		Logger.getLogger(this.getClass().getName()).log(Level.INFO, "out_trade_no"+request.getParameter("out_trade_no"));
		Logger.getLogger(this.getClass().getName()).log(Level.INFO, "alipay:"+request.getParameter("out_trade_no"));
		
		String msg = "success";
		
		String partner = "2088711708694562";
		
		String out_trade_no = request.getParameter("out_trade_no");
		
		String notify_id = request.getParameter("notify_id");
		
		String verifyUrl = "https://mapi.alipay.com/gateway.do?service=notify_verify&partner="+partner+"&notify_id="+notify_id;
		
		String rs =post(verifyUrl,null);
		
		if(!rs.equals("true"))
		{
			return "";
		}
		
		DetachedCriteria criteria = orderService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("tradeNo", out_trade_no));
		
		List<Order> list = orderService.findByCriteria(criteria);
		if(list.isEmpty())
		{
			return "";
		}
		
		//订单状态修改
		Order order = orderService.get(list.get(0).getId());
		order.setStatus(1);
		order.setLastModifiedBy("alipay");
		order.setPayDate(new Date());
		order.setAlipayServer(request.getQueryString());
		orderService.saveOrUpdate(order);
		
		
		//用户财富增加
		User user = order.getUser();
		user.setWealth(user.getWealth()+order.getTotal());
		userService.saveOrUpdate(user);
		
		
		//用户商品增加
		UserProduct userProduct =new UserProduct();
		userProduct.setUser(order.getUser());
		userProduct.setProduct(order.getProduct());
		userProduct.setCreatedDate(new Date());
		userProduct.setAmount(order.getTotal());
		userProduct.setTradeNo(order.getTradeNo());
		userProduct.setStatus(1);
		
		userProductService.save(userProduct);

		
		return msg;
	}
	
	
	/**
	 * 支付宝异步通知
	 * 充值
	 * 
	 * @param from
	 * @param sendTo
	 * @param content
	 */
	@RequestMapping(value = "/notice/charge")
	public String noticeCharge(HttpServletRequest request) {
//		Logger.getLogger("========================");
//		Logger.getLogger(this.getClass().getName()).log(Level.INFO, request.getQueryString());
//		Logger.getLogger(this.getClass().getName()).log(Level.INFO, "out_trade_no"+request.getParameter("out_trade_no"));
		
		Logger.getLogger(this.getClass().getName()).log(Level.INFO, "alipay:"+request.getParameter("out_trade_no"));
		
		String msg = "success";
		
		String partner = "2088711708694562";
		
		String out_trade_no = request.getParameter("out_trade_no");
		
		String notify_id = request.getParameter("notify_id");
		
		String verifyUrl = "https://mapi.alipay.com/gateway.do?service=notify_verify&partner="+partner+"&notify_id="+notify_id;
		
		String rs =post(verifyUrl,null);
		
		if(!rs.equals("true"))
		{
			return "";
		}
		
		
		
		TradeRecord tradeRecord = tradeRecordService.getByTradeNo(out_trade_no);
		
		
		//用户财富增加
		User user = tradeRecord.getUser();
		user.setWealth(user.getWealth()+tradeRecord.getAmount());
		userService.saveOrUpdate(user);
 
		
		return msg;
	}
	
	
	
	/**
	 * 基于HttpClient 4.3的通用POST方法
	 *
	 * @param url
	 *            提交的URL
	 * @param paramsMap
	 *            提交<参数，值>Map
	 * @return 提交响应
	 */
	String post(String url, Map<String, String> paramsMap) {
		CloseableHttpClient client = HttpClients.createDefault();
		String responseText = "";
		CloseableHttpResponse response = null;
		try {
			HttpPost method = new HttpPost(url);
			if (paramsMap != null) {
				List<NameValuePair> paramList = new ArrayList<NameValuePair>();
				for (Map.Entry<String, String> param : paramsMap.entrySet()) {
					NameValuePair pair = new BasicNameValuePair(param.getKey(),
							param.getValue());
					paramList.add(pair);
				}
				method.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));
			}
			response = client.execute(method);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				responseText = EntityUtils.toString(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return responseText;
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
		
		
		SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMddHHmmss");
		String d=sdf.format(new Date());
		
		String tradeNo = "product"+d+entity.getId().toString().replace("-", "");
		
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