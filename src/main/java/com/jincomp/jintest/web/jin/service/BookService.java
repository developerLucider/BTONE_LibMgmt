package com.jincomp.jintest.web.jin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.RequiredArgsConstructor;

import com.jincomp.jintest.web.jin.mapper.BookMapper;
import com.jincomp.jintest.web.jin.vo.BookVO;
import com.jincomp.jintest.web.jin.vo.EventVO;
import com.jincomp.jintest.web.jin.vo.RentVO;

@RequiredArgsConstructor
@Service
public class BookService {
	
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
}
