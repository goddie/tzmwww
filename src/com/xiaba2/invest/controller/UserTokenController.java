package com.xiaba2.invest.controller;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.xiaba2.invest.domain.UserToken;
import com.xiaba2.invest.service.UserTokenService;
@Controller
@RequestMapping("/usertoken")
public class UserTokenController {
@Resource
private UserTokenService userTokenService;
}