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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jincomp.jintest.web.jin.service.BookService;
import com.jincomp.jintest.web.jin.vo.BookVO;
import com.jincomp.jintest.web.jin.vo.UserVO;

@Controller
public class BookController {
	@Autowired
	private static final Logger log = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private BookService bookService;
	
	//책목록 가져오기
	@GetMapping("/")  // 처음 DOMAIN 주소로 접근시 jsp 호출용.
	public String getBookList(HttpServletRequest request,
		HttpServletResponse response, ModelMap model) throws Exception {
		
		return "/main/home";  //home.jsp 로 구성
	}
	
	//대여가능 목록 가져오기
	public String getOklist(HttpServletRequest request,
		HttpServletResponse response, ModelMap model) throws Exception {
		
		return "/main/home";  //home.jsp 로 구성
	}
	
	@GetMapping("/admin")
	public String showAdmin(HttpServletRequest request,
	HttpServletResponse response, ModelMap model) throws Exception {
		
		return "/admin/admin";
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
