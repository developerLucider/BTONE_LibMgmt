package com.jincomp.jintest.web.jin.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.jincomp.jintest.web.jin.service.EventService;
import com.jincomp.jintest.web.jin.vo.EventVO;


@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private EventService eventService;

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

	@GetMapping("/event/add")
	public String adminAddEvent() {

		return "/admin/eventadd";
	}

	@GetMapping("/event/list")
	public String adminListEvent(Model model) {

		List<EventVO> list = eventService.getEventList();
		log.debug("list : {}", list);

		model.addAttribute("list", list);
		
		return "/admin/eventlist";
	}
}

