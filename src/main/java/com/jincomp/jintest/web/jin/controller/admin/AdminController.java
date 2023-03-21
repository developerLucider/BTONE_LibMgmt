package com.jincomp.jintest.web.jin.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin")
public class AdminController {
	
	@GetMapping("/")
	public String adminMain() {
		
		log.info("{}", "관리자 페이지 진입");	
	
		return "/admin/adminhome";
	}
	
	@GetMapping("/add/book")
	public String adminAddBook() {
		
		return "/admin/addbook";
	}
	
	
	@GetMapping("/list/books")
	public String adminBookAll() {
		
		return "/admin/bookAllList";
	}
	
	@GetMapping("/edit/book")
	public String adminBookEdit() {
		
		return "/admin/bookedit";
	}
	
	@GetMapping("/list/rent")
	public String adminRentList() {
		
		return "/admin/rentlist";
	}
	
	@GetMapping("/add/event")
	public String adminAddEvent() {
		
		return "/admin/eventadd";
	}
	
	@GetMapping("/list/event")
	public String adminListEvent() {
		
		return "/admin/eventlist";
	}
}

