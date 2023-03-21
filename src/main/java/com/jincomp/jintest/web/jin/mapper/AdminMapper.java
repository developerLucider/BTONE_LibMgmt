package com.jincomp.jintest.web.jin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jincomp.jintest.web.jin.vo.BookVO;
import com.jincomp.jintest.web.jin.vo.BooksEventInfo;
import com.jincomp.jintest.web.jin.vo.UserVO;

@Mapper
public interface AdminMapper {
	
	List<BookVO> getBookList();

	void insert(BookVO vo);
	void insertEventInfo(BooksEventInfo info);
	
	List<BookVO> getUserList();

	List<BookVO> searchList(String keyword);

	public BookVO getUpBookList(String goodsId);

	int update(BookVO vo);
}
