package com.jincomp.jintest.web.jin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jincomp.jintest.web.jin.vo.EventVO;

@Mapper
public interface EventMapper {
	List<EventVO> getEventList();

	int addEvent(EventVO eventVo);
	
	EventVO getEventVO(String eventId);

	int updateEvent(EventVO eventVo);

	int deleteEvent(@Param("list")List<String> list);
}
