package com.jincomp.jintest.web.jin.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.jincomp.jintest.web.jin.dto.MainBookListDTO;
import com.jincomp.jintest.web.jin.service.HomeService;
import com.jincomp.jintest.web.jin.service.UserService;
import com.jincomp.jintest.web.jin.vo.BookPopWord;
import com.jincomp.jintest.web.jin.vo.BookVO;
import com.jincomp.jintest.web.jin.vo.RentVO;
import com.jincomp.jintest.web.jin.vo.SearchVO;
import com.mysql.cj.xdevapi.JsonArray;

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
       
       /* 검색 할 때 값을 넘겨 받았으면 단어 insert */
       if(searchVO.getKeyword() != null && searchVO.getSearchType().equals("GOODS_NAME")) {
    	   BookPopWord bookPopWord = new BookPopWord();
    	   bookPopWord.setBookPopwWord(searchVO.getKeyword());
    	   homeService.insertSearchBook(bookPopWord.getBookPopwWord());
       } else {
    	   return null;
       }
       
       return list;
   }
   
   /**
    * 검색 자동완성 기능
    * @param paramMap
    * @return
    * @throws Exception
    */
   @RequestMapping(value = "/view/autocomplete")
   public Map<String, Object> searchAutoComplete(@RequestParam Map<String, Object> paramMap) throws Exception {
	   
	   List<Map<String, Object>> resultList = homeService.autoComplete(paramMap);
	   paramMap.put("resultList", resultList);
	   
	   return paramMap;
   }
   
   /**
    * 도서 검색 랭킹
    * @return
    */
   @GetMapping("/list/search/rank")
   public List<BookPopWord> searchBookRankList(){
	   
	   logger.debug("도서 검색 랭킹 컨트롤러 진입");
	   
	   List<BookPopWord> searchBookRankList = homeService.getBookSearchRanking();
	   
	   return searchBookRankList;
   }
   
   @RequestMapping(value = "/rentBooks.do")		// TODO requestParam으로 쪼개받은거 body로 바꾸기
   public Map<String, List<String>> rentBooks(@RequestParam("rentBookList[]")List<String> rentBookList,
		   				@RequestParam("rentBookPriceList[]") List<String> rentBookPriceList,
		   				@RequestParam("rentBookQuantityList[]")List<String> rentBookQuantityList,
		   				@RequestParam("rentBookAgeLimitList[]")List<String> rentBookAgeLimitList,
		   				@RequestParam("userNo") int userNo,
		   HttpServletRequest request, HttpServletResponse response) throws Exception {
	   	logger.debug("대여 책 목록 : {} 대여 유저 정보 : {}", rentBookList, userNo);
		Map<String, List<String>> result = homeService.rentBooks(rentBookList, rentBookPriceList,
				 rentBookQuantityList, rentBookAgeLimitList,  userNo);
	   			
	   	logger.debug("대여 성공 실패 여부 {}", result);
	   	
	   	return result;
   }
}

