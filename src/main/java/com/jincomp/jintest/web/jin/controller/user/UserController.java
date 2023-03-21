package com.jincomp.jintest.web.jin.controller.user;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.jincomp.jintest.web.jin.controller.HomeController;
import com.jincomp.jintest.web.jin.service.UserService;
import com.jincomp.jintest.web.jin.vo.UserAuthVO;
import com.jincomp.jintest.web.jin.vo.UserVO;
import com.mysql.cj.Session;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	@Autowired
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	private final UserService userService;
	
	@PostMapping("/login")
	public String userLogin(UserVO usrVo, HttpServletRequest request, Model model){
		
		log.info("post 로그인");
	   
		// 로그인 정보 가져오기(db에서)
	 	UserVO loginUser = userService.loginUser(usrVo);
	  
	    
	 	// 로그인 정보 세션에 담음.
 		HttpSession httpSession = request.getSession();
 		
 		if( loginUser != null) {
 			// 세션에 로그인 정보 담기
 		    httpSession.setAttribute("loginUser", loginUser);
 			return "redirect:/";
 		} else {
 			// 로그인 실패 시 세션 null 담음.
 		    httpSession.setAttribute("loginUser", null);
 		    model.addAttribute("msg", "존재하지 않는 회원입니다.");
 		     	return "/login/login";
 		}
	}
	/**
	 * 로그아웃
	 * @param httpSession
	 * @return
	 */
	@GetMapping("/logout")
	public String logout(HttpSession httpSession) {
		
		httpSession.invalidate();
		
		return "redirect:/";
	}
	
	/**
	 * 권한 업데이트
	 * @param userNo
	 * @return
	 */
	@PostMapping("/auth/edit/{userNo}")
	@ResponseBody
	public int authUpdate(@PathVariable String userNo, HttpServletRequest request) {
		
		
		// 권한 업데이트(db처리)
		int result = userService.authUpdate(userNo);
				
		// 세션에도 권한 업데이트 시켜서 뿌려주는 작업.
		UserAuthVO authVO = userService.findUserAuth(userNo);		
		
		log.debug("{}", authVO.getUserAuth());
		
		//세션에 다시 넣기
		UserVO loginUser = (UserVO)request.getSession().getAttribute("loginUser");
			
		// 세션도 admin 업데이트
		loginUser.getAuthVO().setUserAuth(authVO.getUserAuth());
					
		return result;
	}
}