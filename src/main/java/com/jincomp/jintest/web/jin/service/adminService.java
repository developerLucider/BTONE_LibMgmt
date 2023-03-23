package com.jincomp.jintest.web.jin.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.internal.compiler.batch.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jincomp.jintest.web.jin.dto.AdminAddBookDTO;
import com.jincomp.jintest.web.jin.dto.UserLentalDTO;
import com.jincomp.jintest.web.jin.mapper.AdminMapper;
import com.jincomp.jintest.web.jin.mapper.BookMapper;
import com.jincomp.jintest.web.jin.vo.BookVO;
import com.jincomp.jintest.web.jin.vo.BooksEventInfo;
import com.jincomp.jintest.web.jin.vo.EventVO;
import com.jincomp.jintest.web.jin.vo.UserVO;

import lombok.RequiredArgsConstructor;

/**
 *
 * @author kyj
 */
@RequiredArgsConstructor
@Service
public class adminService {

	private static final Logger logger = LoggerFactory.getLogger(adminService.class);
	
	private final AdminMapper adminMapper;

	public List<BookVO> getBookList() {
		logger.debug("BookVO 진입");
		
		List<BookVO> mapper = adminMapper.getBookList();
		
		return mapper;
	}

	public List<BookVO> searchList(String search, String searchType) {
		
		return null;
	}
	

//	책등록
	public void insert(AdminAddBookDTO vo) {
		
	
		logger.debug("vo : {}",vo);
//		info.setEventId(vo.getEventId());
		adminMapper.insert(vo);
		adminMapper.insertEventInfo(vo);
	}

	//대여현황 목록
	public List<UserLentalDTO> getUserList() {
		
		logger.debug("------------------ getUserList 진입");
//		List<UserLentalDTO> list = new ArrayList<>();
		
		List<UserLentalDTO> userList = new ArrayList<>();
		List<BookVO> bookList = adminMapper.getUserList();
		UserLentalDTO user = null;
		logger.debug("bookList : {}" , adminMapper.getUserList());
		
		for(BookVO vo : bookList) {
			// 가져온 리스트에서 책별로 필요한 정보만 가져와 DTO에담아서 리스트로 생성
			 user = new UserLentalDTO();
			
			user.setGoodsId(vo.getGoodsId());
			user.setGoodsName(vo.getGoodsName());
			user.setStartDate(vo.getRentVo().getStartDate());
			user.setEndDate(vo.getRentVo().getEndDate());
			user.setUserName(vo.getUserLogin().getUserName());
			
		}    
		//테스트입니다.
		
		userList.add(user);
		
		return userList;
	}

	public List<BookVO> searchList(String keyword) {
		List<BookVO> mapper = adminMapper.searchList(keyword);
		logger.debug("{}", "검색 진입");
		
		return mapper;
	}

	//도서 상세보기
	public BookVO getUpBookList(String goodsId) {
		
		BookVO mapper = adminMapper.getUpBookList(goodsId);
		
		return mapper;
	}
	
	//도서 수정
	public int update(BookVO vo) {
		int mapper = adminMapper.update(vo);
		
		return mapper;
	}
	
	//유저 출력
	public List<UserVO> getUserListt(){
		logger.debug("{}", "getUserList 서비스 진입");
		List<UserVO> getUserListt = adminMapper.getUserListt();
		
		return getUserListt;
	}
	
	//유저 이름 검색
	public List<UserVO> searchUserList(String userKeyWord) {
		List<UserVO> searchUserList = adminMapper.searchUserList(userKeyWord);
		logger.debug("{}", "검색 진입");
		
		return searchUserList;
	}
	
}
