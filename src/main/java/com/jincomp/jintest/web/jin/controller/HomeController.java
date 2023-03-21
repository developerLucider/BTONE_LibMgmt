package com.jincomp.jintest.web.jin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.jincomp.jintest.web.jin.dto.MainBookListDTO;
import com.jincomp.jintest.web.jin.service.HomeService;

@Controller
public class HomeController {
	@Autowired
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private HomeService userService;
	
	@GetMapping("/")  // 처음 DOMAIN 주소로 접근시 jsp 호출용.
	public String showFirstHome(HttpServletRequest request,
		HttpServletResponse response, ModelMap model) throws Exception {
		logger.debug("showFirstHome 진입");
		
		List<MainBookListDTO> list = userService.getMainBookList();
		
		logger.debug("list : {}", list);
		
		model.addAttribute("list", list);
		
		return "/main/home";  //home.jsp 로 구성
	}
	
	@GetMapping("/admin")
	public String showAdmin(HttpServletRequest request,
	HttpServletResponse response, ModelMap model) throws Exception {
		
		return "/admin/adminhome";
	}
	
	@GetMapping("/regist")
	public String showRegist(HttpServletRequest request,
	HttpServletResponse response, ModelMap model) throws Exception {
		
		return "/regist/regist";
	}
	
	@GetMapping("/login")
	public String showLogin(HttpServletRequest request,
	HttpServletResponse response, ModelMap model) throws Exception {
		
		return "/login/login";
	}
}