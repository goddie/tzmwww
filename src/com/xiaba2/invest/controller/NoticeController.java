package com.xiaba2.invest.controller;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.xiaba2.invest.domain.Notice;
import com.xiaba2.invest.service.NoticeService;
@Controller
@RequestMapping("/notice")
public class NoticeController {
@Resource
private NoticeService noticeService;
}