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

import com.jincomp.jintest.web.jin.service.UserService;
import com.jincomp.jintest.web.jin.vo.BookVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class HomeController {
	@Autowired
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private UserService userService;


	@GetMapping("/") // 처음 DOMAIN 주소로 접근시 jsp 호출용.
	public String showFirstHome(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		
		// bookList 가져오는 로직
		List<BookVO> list = userService.bookList();
		log.debug("list : {}",list);
		
		model.addAttribute("list", list);		

		return "/main/home"; // home.jsp 로 구성s
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 * 관리자 페이지 이동 
	 */
	@GetMapping("/admin")
	public String showAdmin(HttpServletRequest request,
	HttpServletResponse response, ModelMap model) throws Exception {
		
		return "/admin/adminhome";
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 * 회원가입 페이지 이동
	 */
	@GetMapping("/regist")
	public String showRegist(HttpServletRequest request,
	HttpServletResponse response, ModelMap model) throws Exception {
		
		return "/regist/regist";
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 * 로그인 페이지 이동
	 */
	@GetMapping("/login")
	public String showLogin(HttpServletRequest request,
	HttpServletResponse response, ModelMap model) throws Exception {
		
		return "/login/login";
	}

}
