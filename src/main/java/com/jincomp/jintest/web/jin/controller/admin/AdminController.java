package com.jincomp.jintest.web.jin.controller.admin;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.jincomp.jintest.web.jin.dto.AdminAddBookDTO;
import com.jincomp.jintest.web.jin.dto.UserLentalDTO;
import com.jincomp.jintest.web.jin.service.EventService;
import com.jincomp.jintest.web.jin.service.adminService;
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
	private EventService eventService;
	
	
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
		List<EventVO> event = this.eventIdList();
		model.addAttribute("list", bookList);
		model.addAttribute("event", event);
	
		log.debug("book : {}", bookList);		
		
		return "/admin/book/bookedit";
	}
	@ModelAttribute("event")
	public List<EventVO> eventIdList(){
		List<EventVO> event  = eventService.getEventList();
		
		return event;
	}
	
	@GetMapping("/books/rent")
	public String rentList(HttpServletRequest request,
		   HttpServletResponse response, ModelMap model) throws Exception {
		List<UserLentalDTO> rentList = adminService.rentList();
		
		log.debug("rentList : {}", rentList);
		
		model.addAttribute("list", rentList);
		
		return "/admin/book/rentlist";
	}
	
	@GetMapping("/event/add")
	public String adminAddEvent() {

		return "/admin/event/eventadd";
	}

	@GetMapping("/event/list")
	public String adminListEvent(Model model) {

		List<EventVO> list = eventService.getEventList();
		log.debug("list : {}", list);

		model.addAttribute("list", list);
		
		return "/admin/event/eventlist";
	}

	
	//어드민 유저 리스트 출력
	@GetMapping("/member/adminList")
	public String getUserList(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		List<UserVO> userList = adminService.userList();
		model.addAttribute("list", userList);
			
			
			return "/admin/member/adminUserList";  
		}
	}

