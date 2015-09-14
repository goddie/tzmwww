package com.xiaba2.invest.controller;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.invest.domain.Product;
import com.xiaba2.invest.domain.ProductDetail;
import com.xiaba2.invest.domain.ProductInfo;
import com.xiaba2.invest.service.ProductDetailService;
import com.xiaba2.invest.service.ProductService;

@Controller
@RequestMapping("/productdetail")
public class ProductDetailController {
	@Resource
	private ProductDetailService productDetailService;
	
	@Resource
	private ProductService productService;

	@RequestMapping(value = "/page/detail")
	public ModelAndView pageDetail(@RequestParam("pid") String pid,@RequestParam("type") int type)
	{
		ModelAndView mv = new ModelAndView("product_detail");

		UUID id = UUID.fromString(pid);

		Product entity = productService.get(id);

		if (entity == null) {
			return mv;
		}

		DetachedCriteria criteria = productDetailService.createDetachedCriteria();
		
		criteria.add(Restrictions.eq("type", type));
		criteria.add(Restrictions.eq("product", entity));

		List<ProductDetail> list = productDetailService.findByCriteria(criteria);

		mv.addObject("list", list);

		return mv;
		
	}
}