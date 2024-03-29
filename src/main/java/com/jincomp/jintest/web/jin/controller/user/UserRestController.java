package com.jincomp.jintest.web.jin.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.jincomp.jintest.web.jin.service.UserService;
import com.jincomp.jintest.web.jin.vo.UserLogin;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(value = "/main")
@RestController
public class UserRestController {

	private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

	
	private final UserService userService;
	
	@RequestMapping(value = "/getjoin.do")
	public int join(UserLogin user, Model model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.debug("{}" ,"회원가입 진입");
		int joinch = userService.join(user);
		
		return joinch;
	}
	
	//아이디 중복체크하기
	@ResponseBody
	@RequestMapping(value="/idChk", method = RequestMethod.POST)
	public Boolean idChk(@RequestBody UserLogin user) throws Exception {
		
		Boolean result = userService.idChk(user);
		logger.debug("{}" ,"중복체크 진입");
		
		return result;
	}


	//회원정보 수정
	@RequestMapping(value = "/userUpdate.do")
	public int userUpdate(@RequestBody UserLogin userLoginVo,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.debug("{}" ,"회원수정 진입");
		int user = userService.userUpdate(userLoginVo, request);
		
		logger.debug("vo : {}", userLoginVo);
		return user;
	}
	
}
