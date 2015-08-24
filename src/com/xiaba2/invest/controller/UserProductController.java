package com.xiaba2.invest.controller;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.xiaba2.invest.domain.UserProduct;
import com.xiaba2.invest.service.UserProductService;
@Controller
@RequestMapping("/userproduct")
public class UserProductController {
@Resource
private UserProductService userProductService;
}