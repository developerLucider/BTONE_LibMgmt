package com.jincomp.jintest.web.jin.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jincomp.jintest.web.jin.dto.AdminAddBookDTO;
import com.jincomp.jintest.web.jin.dto.UserLentalDTO;
import com.jincomp.jintest.web.jin.service.EventService;
import com.jincomp.jintest.web.jin.service.UserService;
import com.jincomp.jintest.web.jin.service.adminService;
import com.jincomp.jintest.web.jin.vo.BookVO;
import com.jincomp.jintest.web.jin.vo.EventVO;
import com.jincomp.jintest.web.jin.vo.UserVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin")

public class AdminController {
	
	@Autowired
	private adminService adminService;
	@Autowired
	private EventService EventService;
	
	
	@GetMapping("/")
	public String adminMain() {
		
		log.info("{}", "관리자 페이지 진입");	
	
		return "/admin/adminhome";
	}
	
	//책등록
	@GetMapping("/add/book")
	public String adminAddBook() {
		
		return "/admin/book/addbook";
	}
	
	//책 전체조회
	@GetMapping("/list/books")
	public String adminBookAll(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
		
		List<AdminAddBookDTO> bookList = adminService.getBookList();
		log.debug("bookList : {}", bookList);
		
		model.addAttribute("list", bookList);
			
		return "/admin/book/bookAllList";
	}
	
	
	//책상세보기
	@GetMapping("/edit/book/{goodsId}")
	public String adminBookEdit(@PathVariable String goodsId,
			HttpServletResponse response, ModelMap model) throws Exception {
		log.debug("goodsId : ",goodsId);
		log.debug("{}", "수정진입" );
		
		AdminAddBookDTO bookList = adminService.getUpBookList(goodsId);
		
		model.addAttribute("list", bookList);
		log.debug("book : {}", bookList);
		
		return "/admin/book/bookedit";
	}
	
	@GetMapping("/books/rent")
	public String adminRentList(HttpServletRequest request,
		   HttpServletResponse response, ModelMap model) throws Exception {
		List<UserLentalDTO> userList = adminService.getUserList();
		
		log.debug("userList : {}", userList);
		
		model.addAttribute("userList", userList);
		
		return "/admin/book/rentlist";
	}
	
	@GetMapping("/event/add")
	public String adminAddEvent() {

		return "/admin/event/eventadd";
	}

	@GetMapping("/event/list")
	public String adminListEvent(Model model) {

		List<EventVO> list = EventService.getEventList();
		log.debug("list : {}", list);

		model.addAttribute("list", list);
		
		return "/admin/event/eventlist";
	}

	
	//어드민 유저 리스트 출력
	@GetMapping("/user/list")
	public String getUserList(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		List<UserVO> getUserListt = adminService.getUserListt();
		model.addAttribute("getUserListt", getUserListt);
			
			
			return "/admin/member/adminUserList";  
		}
	}

