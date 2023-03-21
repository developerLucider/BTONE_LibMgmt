package com.jincomp.jintest.web.jin.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.common.Base64Utils;
import com.jincomp.jintest.web.jin.mapper.BookMapper;
import com.jincomp.jintest.web.jin.mapper.UserMapper;
import com.jincomp.jintest.web.jin.vo.BookVO;
import com.jincomp.jintest.web.jin.vo.EventVO;
import com.jincomp.jintest.web.jin.vo.UserLogin;
import com.jincomp.jintest.web.jin.vo.UserVO;

import lombok.RequiredArgsConstructor;

/**
 *
 * @author kyj
 */
@RequiredArgsConstructor
@Service
public class BookService {

	private static final Logger logger = LoggerFactory.getLogger(BookService.class);
	
	private final BookMapper bookMapper;

	public List<BookVO> getBookList() {
		logger.debug("BookVO 리스트 진입");
		
		List<BookVO> mapper = bookMapper.getBookList();
		
		return mapper;
	}

	//검색
	public List<BookVO> searchList(String search, String searchType) {
		List<BookVO> mapper = bookMapper.searchList(search, searchType);
		logger.debug(" 검색 진입");
		
		return mapper;
	}

	//회원가입
	public int join(UserLogin user) {
		logger.debug("user : {}", user);
		
		int joinck = 0;
		//중복된 아이디가 없으면
		if (idChk(user) == true) {

			String pass = user.getUserPassword(); // user에 가져온 비번
			String pwd = Base64Utils.base64Encoder(pass);
//
			user.setUserPassword(pwd);
			logger.debug("user : {}", user);

			joinck = bookMapper.join(user);
		}
		return joinck;
	}
	
	//아이디 중복체크
	public Boolean idChk(UserLogin user) {
		int result = bookMapper.idChk(user);
		
		//중복된 아이디가 없음
		if(result == 0) {
			return true;
		}
		return false;
	}
	
	
	
}
