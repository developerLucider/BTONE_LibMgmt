package com.jincomp.jintest.web.jin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jincomp.jintest.web.jin.mapper.BookMapper;
import com.jincomp.jintest.web.jin.vo.BookVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private BookMapper bookMapper;

	/**
	 * @author sojin
	 * @return
	 *  렌트 현황리스트 조회 
	 */
	public List<BookVO> rentList() {
		
		return  bookMapper.rentList();
	}

	public List<BookVO> bookList() {
		
		return bookMapper.bookList();
	}
}
