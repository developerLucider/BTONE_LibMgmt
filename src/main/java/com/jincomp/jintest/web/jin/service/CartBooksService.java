package com.jincomp.jintest.web.jin.service;

import org.springframework.stereotype.Service;

import com.jincomp.jintest.web.jin.mapper.CartBooksMapper;
import com.jincomp.jintest.web.jin.vo.CartBooksVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartBooksService {

	private final CartBooksMapper booksMapper;
	
	public int insertCart(CartBooksVO booksVO) {
		
		log.info("cart insert service 접근");
		
		int result = booksMapper.insertCart(booksVO);
		
		if (result >= 1) {
			
			return result;
		} else {
			
			return -1;
		}
				
	}

}
