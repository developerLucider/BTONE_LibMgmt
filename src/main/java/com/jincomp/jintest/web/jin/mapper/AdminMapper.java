package com.jincomp.jintest.web.jin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jincomp.jintest.web.jin.dto.AdminAddBookDTO;
import com.jincomp.jintest.web.jin.vo.BookVO;
import com.jincomp.jintest.web.jin.vo.BooksEventInfo;
import com.jincomp.jintest.web.jin.vo.UserVO;

@Mapper
public interface AdminMapper {
	
	List<AdminAddBookDTO> getBookList();

	void insert(AdminAddBookDTO vo);
	void insertEventInfo (AdminAddBookDTO vo);
	void insertBookAge(AdminAddBookDTO vo);
	void insertBookQty(AdminAddBookDTO vo);
	
	List<BookVO> getUserList();

	List<BookVO> searchList(String keyword);

	public AdminAddBookDTO getUpBookList(String goodsId);

	void update(AdminAddBookDTO vo);
	void updateEventInfo(AdminAddBookDTO vo);
	void updateBookAge(AdminAddBookDTO vo);
	void updateBookQty(AdminAddBookDTO vo);

	
	int update(BookVO vo);
	
	//유저 리스트 출력
	List<UserVO> getUserListt();
	//유저 이름 검색
	List<UserVO> searchUserList(String userKeyWord);




  
}
