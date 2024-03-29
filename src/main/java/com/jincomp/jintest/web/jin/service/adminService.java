package com.jincomp.jintest.web.jin.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.internal.compiler.batch.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.util.BeanUtil;
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

	//전체조회
	public List<AdminAddBookDTO> getBookList() {
		logger.debug("AdminAddDto 진입");
		
		List<AdminAddBookDTO> mapper = adminMapper.getBookList();
		
		return mapper;
	}

	
//	public List<BookVO> searchList(String search, String searchType) {
//		
//		return null;
//	}
	

//	책등록
	public void insert(AdminAddBookDTO vo) {
	
//		BeanUtils.copyProperties(vo, vo);//복사
		
		logger.debug("vo : {}",vo);
//		info.setEventId(vo.getEventId());
		adminMapper.insert(vo);
		adminMapper.insertEventInfo(vo);
		adminMapper.insertBookAge(vo);
		adminMapper.insertBookQty(vo);
	}

	//대여현황 목록
	public List<UserLentalDTO> rentList() {
		
		logger.debug("------------------ getUserList 진입");
		
		List<UserLentalDTO> userList = new ArrayList<>();
		List<BookVO> bookList = adminMapper.rentList();
		logger.debug("bookList : {}" , adminMapper.rentList());
		
		for(BookVO vo : bookList) {
			// 가져온 리스트에서 책별로 필요한 정보만 가져와 DTO에담아서 리스트로 생성
			UserLentalDTO user = new UserLentalDTO();
			
			user.setGoodsId(vo.getGoodsId());
			user.setGoodsName(vo.getGoodsName());
			user.setStartDate(vo.getRentVo().getStartDate());
			user.setEndDate(vo.getRentVo().getEndDate());
			user.setUserName(vo.getUserLogin().getUserName());
			
			userList.add(user);
		}
		logger.debug("user: {}", " book :{}" , userList, bookList);
		
		
		return userList;
	}

	public List<BookVO> searchList(String keyword) {
		List<BookVO> mapper = adminMapper.searchList(keyword);
		logger.debug("{}", "검색 진입");
		logger.debug("{}", mapper);
		
		return mapper;
	}

	//도서 상세보기
	public AdminAddBookDTO getUpBookList(String goodsId) {
		
		AdminAddBookDTO mapper = adminMapper.getUpBookList(goodsId);
		
		return mapper;
	}
	
	//도서 수정
	public void update(AdminAddBookDTO vo) {
		logger.debug("수정vo : {}",vo);
		
		adminMapper.update(vo);
		adminMapper.updateEventInfo(vo);
		adminMapper.updateBookAge(vo);
		adminMapper.updateBookQty(vo);
		
	}
	
	//유저 출력
	public List<UserVO> userList(){
		logger.debug("{}", "getUserList 서비스 진입");
		List<UserVO> userList = adminMapper.userList();
		
		logger.debug("adminList : {}", userList);
		
		return userList;
	}
	
	//유저 이름 검색
	public List<UserVO> searchUserList(String userKeyWord) {
		List<UserVO> searchUserList = adminMapper.searchUserList(userKeyWord);
		logger.debug("{}", "검색 진입");
		
		return searchUserList;
	}
	
	
}
