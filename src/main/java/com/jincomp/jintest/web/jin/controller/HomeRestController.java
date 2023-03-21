package com.jincomp.jintest.web.jin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jincomp.jintest.web.jin.dto.MainBookListDTO;
import com.jincomp.jintest.web.jin.service.HomeService;
import com.jincomp.jintest.web.jin.vo.RentVO;
import com.jincomp.jintest.web.jin.vo.SearchVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(value = "/main")
@RestController
public class HomeRestController {

	private static final Logger logger = LoggerFactory.getLogger(HomeRestController.class);
	
	private final HomeService homeService;

   @RequestMapping(value = "/getBooks.do")
   public List<MainBookListDTO> searchBookList(@RequestBody SearchVO searchVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
	   logger.debug("{} {}",searchVO.getSearchType(), searchVO.getKeyword());
	   List<MainBookListDTO> list = homeService.getMainBookList(searchVO.getSearchType(), searchVO.getKeyword());
	   
       logger.debug("검색타입 : {} 키워드 : {}", searchVO.getSearchType(), searchVO.getKeyword());
       
       return list;
   }
   
   @RequestMapping(value = "/rentBooks.do")
   public List<Integer> rentBooks(@RequestParam("rentBookList[]")List<String> rentBookList,
		   				@RequestParam("userNo") int userNo,
		   HttpServletRequest request, HttpServletResponse response) throws Exception {
	   	logger.debug("대여 책 목록 : {} 대여 유저 정보 : {}", rentBookList, userNo);
	   	
	   	return homeService.rentBooks(rentBookList, userNo);
   }
}

