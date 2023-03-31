package com.jincomp.jintest.web.jin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.jincomp.jintest.web.jin.dto.MainBookListDTO;
import com.jincomp.jintest.web.jin.service.BookService;
import com.jincomp.jintest.web.jin.service.HomeService;
import com.jincomp.jintest.web.jin.service.UserService;
import com.jincomp.jintest.web.jin.vo.BookVO;
import com.jincomp.jintest.web.jin.vo.OrderVO;
import com.jincomp.jintest.web.jin.vo.UserLogin;

@Controller
public class HomeController {
	@Autowired
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private HomeService homeService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;

	@GetMapping("/")
	public String showFirstHome(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		logger.debug("showFirstHome 진입");

		List<MainBookListDTO> list = homeService.getMainBookList();

		logger.debug("list : {}", list);
		

		model.addAttribute("list", list);
		
		//exception test 중
		/*
		 * throw new BtoneException(ErrorCode.NOT_SUPPORTED_HTTP_METHOD); try { throw
		 * new BtoneException(ErrorCode.NOT_SUPPORTED_HTTP_METHOD); }
		 * catch(BtoneException e) { logger.error(e.getMessage());
		 * model.addAttribute("errorCode", e.getErrorCode()); return "error/error"; }
		 */

		return "home"; // home.html로 이동
	}
	// test
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
	

//	
//	//성인인증 페이지 진입
//	@GetMapping("/adult")
//	public String showAdult(HttpServletRequest request,
//
//	HttpServletResponse response, Model model) throws Exception {
//		
//		return "/test/adult";
//	}
//	
//	
//	//페이지에서 인증
//	@PostMapping("/adult")
//	public String adult(UserVO userVO,@RequestParam("userRegNo1") String userRegNo1, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
//		
//		logger.debug("{}", "깐트롤러 진입");
//		
////		logger.debug("userRegNo1 : {}", userRegNo1);
//		
//		UserVO adultUser = homeService.adult(userVO, request, userRegNo1);
//		if(adultUser != null) {
//			response.setContentType("text/html; charset=UTF-8");
//			PrintWriter out = response.getWriter();
////			out.println("<script>alert('인증 완료했습니다.'); location.href='/'; </script>");
//			
//			out.println("<script>alert('인증 완료했습니다.'); opener.document.location.reload(); self.close(); </script>");
//			
//			out.flush();
//		} else if(adultUser == null) {
//			response.setContentType("text/html; charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			out.println("<script>alert('정보를 확인해주세요.'); history.go(-1); </script>");
//			out.flush();
//			
//		}
//		return "/test/adult";
//	}
		
	@GetMapping("/mypage/{userNo}")
	public String showMypage(@PathVariable int userNo, HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
	logger.debug("mypage진입");
		
		logger.debug("userNo : {}", userNo);
		
		List<OrderVO> list = userService.orderList(userNo);   // userNo를 매개변수로 받아 로그인된 회원의 주문 내역리슽트만 가져옴
		
		logger.debug("list : {}", list);
		
		model.addAttribute("list", list);
		
		
		//사용자 정보 조회
		UserLogin user =  userService.userPage(userNo);
		logger.debug("user : {}", user);
		
		model.addAttribute("user :{} ", user);


		return "/user/mypage"; // mypage 폴더의 mypage화면으로 이동.
	}
	
	
	/**
	 * 도서 상세 보기 폼
	 * @return
	 */
	@GetMapping("/detail/book")
	public String detailBook(@RequestParam("goodsId") String goodsId,
							 Model model){
		
		logger.debug("도서 상세보기 컨트롤러 진입");
		
		BookVO detailBook = bookService.detailBook(goodsId);
		
		logger.debug("쿼리를 타서 원하는 값이 잘 담겼나? : {}", detailBook);
		
		model.addAttribute("book", detailBook);
		
		return "/book/detail";
	}
}
