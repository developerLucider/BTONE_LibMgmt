package com.jincomp.jintest.web.jin.controller.admin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jincomp.jintest.web.jin.service.EventService;
import com.jincomp.jintest.web.jin.vo.EventVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(value = "/admin/event/")
@RestController
public class EventController {

	private static final Logger logger = LoggerFactory.getLogger(EventController.class);

	
	private final EventService eventService;


   @PostMapping(value="add/addEvent.do")
   public String addEvent(@RequestBody EventVO eventVo)throws Exception {   
	   logger.debug("등록할 이벤트 : {}", eventVo);
	   return eventService.addEvent(eventVo);
   }
   
   @PostMapping(value="list/update.do")
   public String updateEvent(@RequestBody EventVO eventVo)throws Exception{
	   logger.debug("수정할 이벤트 : {}", eventVo);
	   return eventService.updateEvent(eventVo);
   }
   
   @PostMapping(value="list/delete.do")
   public String deleteEvent(@RequestParam("list[]")List<String> list) {
	   logger.debug("삭제 목록 : {}", list);
	   return eventService.deleteEvent(list);
   }
   
}

