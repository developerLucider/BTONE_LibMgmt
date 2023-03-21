package com.jincomp.jintest.web.jin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jincomp.jintest.web.jin.vo.BookVO;
import com.jincomp.jintest.web.jin.vo.RentVO;

@Mapper
public interface BookMapper {	
	List<BookVO> getBookList();

	List<BookVO> searchBookList(@Param("searchType") String searchType,
								@Param("keyword")String keyword);
	
	List<RentVO> getRentList();
	int addRentBook(RentVO rentVo);
}
