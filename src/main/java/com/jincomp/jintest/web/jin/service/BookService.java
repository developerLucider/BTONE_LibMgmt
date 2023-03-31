package com.jincomp.jintest.web.jin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jincomp.jintest.web.jin.mapper.BookMapper;
import com.jincomp.jintest.web.jin.vo.BookVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
	
	private static final Logger logger = LoggerFactory.getLogger(BookService.class);

	private final BookMapper bookMapper;
	
	/**
	 * 도서 상세보기 
	 * @param goodsId
	 * @return
	 */
	public BookVO detailBook(String goodsId) {
		
		logger.debug("도서 상세보기 service 진입");
		
		BookVO detailBook = bookMapper.detailBook(goodsId);
		
		return detailBook;
	}
}
