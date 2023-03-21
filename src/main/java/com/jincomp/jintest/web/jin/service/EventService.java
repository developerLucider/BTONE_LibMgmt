package com.jincomp.jintest.web.jin.service;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.jincomp.jintest.web.jin.mapper.EventMapper;
import com.jincomp.jintest.web.jin.vo.EventVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EventService {

	private static final Logger logger = LoggerFactory.getLogger(EventService.class);

	private final EventMapper eventMapper;
	
	/**
	 * @author sojin
	 * @return
	 * 이벤트 조회 페이지 클릭후 첫 페이지 
	 */
	public List<EventVO> getEventList() {
		logger.debug("서비스 getEventList 진입");

		logger.debug("list : {}", eventMapper.getEventList());

		return eventMapper.getEventList();
	}
	/**
	 * @author sojin
	 * @param eventVo
	 * @return
	 * 이벤트 등록 
	 */
	public String addEvent(EventVO eventVo) {
		logger.debug("서비스 addEvent 진입");

		String result = "";

		logger.debug("등록 이벤트 : {}", eventVo);
		if (eventVo.getEventId() != null) { // evventId가 null값이 아니면

			EventVO event = eventMapper.getEventVO(eventVo.getEventId()); // 현재 eventid리스트를 가져와서

			if (!Objects.isNull(event)) { // 새로등록할 eventId가 있는지 체크
				result = "같은 ID의 이벤트가 존재합니다.";
			} else { // 같은id가 없다면
				int info = eventMapper.addEvent(eventVo); // 새이벤트 등록
				result = (info == 1) ? "등록되었습니다." : "등록 실패"; // 등록 결과를 받아서 출력.
			}
		}
		return result;
	}

	/**
	 * @author sojin
	 * @param eventVo
	 * @return
	 * 이벤트 수정
	 */
	public String updateEvent(EventVO eventVo) {
		logger.debug("서비스 updateEvent 진입");
		String result = "";

		logger.debug("수정할 이벤트 : {}", eventVo);
		EventVO event = eventMapper.getEventVO(eventVo.getEventId()); // 이벤트 기존 정보

		if (event.equals(eventVo)) {
			result = "변경된 정보가 없습니다.";
		} else {
			int info = eventMapper.updateEvent(eventVo);
			result = (info == 1) ? "수정되었습니다." : "수정 실패";
		}
		return result;
	}
	/**
	 * @author sojin
	 * @param list
	 * @return
	 * 이벤트 삭제
	 */
	public String deleteEvent(List<String> list) {
		logger.debug("서비스 deleteEvent 진입");
		String result="";
		
		logger.debug("삭제 목록 : {}", list);
		if(!Objects.isNull(list)) {
			int info = eventMapper.deleteEvent(list);
			result = (info >= 1) ? "삭제되었습니다." : "삭제 실패";
		}		
		return result;
	}
}
