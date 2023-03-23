package com.jincomp.jintest.web.jin.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jincomp.jintest.web.jin.dto.AdminAddBookDTO;
import com.jincomp.jintest.web.jin.service.adminService;
import com.jincomp.jintest.web.jin.vo.BookVO;
import com.jincomp.jintest.web.jin.vo.BooksEventInfo;
import com.jincomp.jintest.web.jin.vo.UserVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(value = "/admin")
@RestController
public class AdminRestController {

	private static final Logger logger = LoggerFactory.getLogger(AdminRestController.class);

	
	private final adminService adminService;

	
	@RequestMapping(value = "/add/book.do")	
	public void insert(@RequestBody AdminAddBookDTO vo) throws Exception {

		logger.debug("{}" ,"게시글쓰기 진입");
		
		logger.debug("eventId : {}" , vo);
		
		adminService.insert(vo);		 
		
	}
	
	//검색
	@RequestMapping(value = "/getAdminSearch.do")
	   public List<BookVO> tableList(@RequestParam ("keyword") String keyword, 
			   HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.debug("{}" ,"검색창 진입");

		List<BookVO> bookList = adminService.searchList(keyword);
		logger.debug("bookList : {}" ,bookList); //{}를 꼭 넣어줘야함.

		return bookList;
	}
	
	//책 수정
	@RequestMapping(value = "/update.do")	//타입 확인해주기!
	public void update(@RequestBody AdminAddBookDTO vo) throws Exception {

		logger.debug("{}" ,"글 수정 진입");
		logger.debug("vo : {}", vo);
		
		 adminService.update(vo);
	}
}


