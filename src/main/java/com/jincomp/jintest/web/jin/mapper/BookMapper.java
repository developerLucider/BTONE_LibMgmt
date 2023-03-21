package com.jincomp.jintest.web.jin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jincomp.jintest.web.jin.vo.BookVO;
import com.jincomp.jintest.web.jin.vo.UserLogin;
import com.jincomp.jintest.web.jin.vo.UserVO;

@Mapper
public interface BookMapper {
	
	List<BookVO> getBookList();

	List<BookVO> searchList(@Param ("search") String search, @Param ("searchType") String searchType);

	int idChk(UserLogin user);

	int join(UserLogin user);
	
}
