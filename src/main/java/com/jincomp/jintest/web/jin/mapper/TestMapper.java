package com.jincomp.jintest.web.jin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jincomp.jintest.web.jin.dto.AdminAddBookDTO;
import com.jincomp.jintest.web.jin.vo.BookVO;
import com.jincomp.jintest.web.jin.vo.BooksEventInfo;
import com.jincomp.jintest.web.jin.vo.UploadVO;
import com.jincomp.jintest.web.jin.vo.UserVO;

@Mapper
public interface TestMapper {
	
	List<UploadVO> getImageList();
	
	void insertImage(UploadVO image);
}
