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
import org.springframework.web.bind.annotation.PathVariable;

import com.jincomp.jintest.web.jin.dto.MainBookListDTO;
import com.jincomp.jintest.web.jin.dto.OrderDTO;
import com.jincomp.jintest.web.jin.service.HomeService;
import com.jincomp.jintest.web.jin.service.UserService;
import com.jincomp.jintest.web.jin.vo.OrderVO;

@Controller
public class HomeController {
	@Autowired
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private HomeService homeService;

	@Autowired
	private UserService userService;

	@GetMapping("/") // 처음 DOMAIN 주소로 접근시 jsp 호출용.
	public String showFirstHome(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		logger.debug("showFirstHome 진입");

		List<MainBookListDTO> list = homeService.getMainBookList();

		logger.debug("list : {}", list);
		

		model.addAttribute("list", list);

		return "home"; // home.html로 이동
	}

	@GetMapping("/admin")
	public String showAdmin(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		return "/admin/adminhome";
	}

	@GetMapping("/regist")
	public String showRegist(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {

		return "/user/regist";
	}

	@GetMapping("/login")
	public String showLogin(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		return "/user/login";
	}
	
	//성인인증 페이지 진입
	@GetMapping("/adult")
	public String showAdult(HttpServletRequest request,
	HttpServletResponse response, ModelMap model) throws Exception {
		
		return "/test/adult";
	}


	@GetMapping("/mypage/{userNo}")
	public String showMypage(@PathVariable int userNo, HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
	logger.debug("mypage진입");
		
		logger.debug("userNo : {}", userNo);
		
		List<OrderVO> list = userService.orderList(userNo);   // userNo를 매개변수로 받아 로그인된 회원의 주문 내역리슽트만 가져옴
		
		logger.debug("list : {}", list);

		model.addAttribute("list", list);

		return "/user/mypage"; // mypage 폴더의 mypage화면으로 이동.
	}
}
