package com.jincomp.jintest.web.jin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.common.Base64Utils;
import com.jincomp.jintest.web.jin.mapper.BookMapper;
import com.jincomp.jintest.web.jin.vo.BookVO;
import com.jincomp.jintest.web.jin.vo.UserLogin;

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
