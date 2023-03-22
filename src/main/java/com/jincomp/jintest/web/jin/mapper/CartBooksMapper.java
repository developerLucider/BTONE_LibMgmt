package com.jincomp.jintest.web.jin.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.jincomp.jintest.web.jin.vo.CartBooksVO;

@Mapper
public interface CartBooksMapper {

	Optional<CartBooksVO> findByUserIdAndBookId(int userNo, String bookId);
	
	int insertCart(CartBooksVO booksVO);
}
