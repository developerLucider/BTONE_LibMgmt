package com.jincomp.jintest.web.jin.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jincomp.jintest.web.jin.dto.OrderDTO;
import com.jincomp.jintest.web.jin.mapper.UserMapper;
import com.jincomp.jintest.web.jin.vo.OrderVO;
import com.jincomp.jintest.web.jin.vo.UserAuthVO;
import com.jincomp.jintest.web.jin.vo.UserVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	private final UserMapper userMapper;

	/**
	 * 로그인
	 * @param userId
	 * @param userPassword
	 * @return
	 */
	public UserVO loginUser(UserVO userVo) {
		
		logger.debug("로그인 서비스 진입");
		
		Base64.Decoder decoder = Base64.getDecoder();
		
	 	// 클라이언트에서 받은 값.
	 	String newId = userVo.getUserId();
	    String newPw = userVo.getUserPassword();
	    
	    logger.debug("클라이언트에서 받은 아이디 : {}", newId);
	    logger.debug("클라이언트에서 받은 비밀번호 : {}", newPw);
	    
	    // 로그인 정보(db)
	 	UserVO loginUser = userMapper.loginUser(userVo);

	    
	    if(loginUser != null) {	 	    			
	      // 디비 값 
		  String oldId = loginUser.getUserId();
		  String oldPw = loginUser.getUserPassword();
		  
		  logger.debug("디비에 저장된 아이디 : {}", oldId);
		  logger.debug("디비에 저장된 암호화 비밀번호 : {}", oldPw);   //인코딩된 비밀번호 안나옴.
	      
		  // 비밀번호 디코딩
		  String decodPw = new String (decoder.decode(oldPw));
		  logger.debug("디코딩된 비밀번호 : {}", decodPw);
		  
		    
		  if(newId.equals(oldId) && newPw.equals(decodPw)) {		    	
			  return loginUser;
		  }  else {
			  return null;
		  }
		   
	    }
	    	return loginUser;
	}
		
	/**
	 * 권한 업데이트
	 * @param userNo
	 * @return
	 */
	public int authUpdate(String userNo) {
		
		logger.debug("권한 서비스 업데이트 진입");
			
		UserAuthVO authVO = userMapper.findUserAuth(userNo);
		
		int count = userMapper.authUpdate(userNo);
		
		try {
			
			if(authVO.getUserAuth() == null || authVO.getUserAuth().equals("")) {
				
				return -1;
			
			} else {
				
				return count;
			}
		} catch (NullPointerException e) {
			logger.debug("{}", "권한이 비어있습니다.");
			
		}
			
		return count;
	}
	
	
	/**
	 * 유저 권한 찾기
	 * @param userNo
	 * @return
	 */
	public UserAuthVO findUserAuth(String userNo) {
		
		UserAuthVO authVO = userMapper.findUserAuth(userNo);
		
		return authVO;
	}

	/**
	 * @author sojin
	 * @param userNo
	 * @return
	 * 주문내역 리스트 조회
	 */
	public List<OrderVO> orderList(int userNo) {
		
//		List<OrderDTO> order = new ArrayList<>();
//		List<OrderVO> list = userMapper.orderList(userNo);  // 현재 회원의 주문내역 리스트 
//		
//		for(OrderVO vo :list) {
//			
//			OrderDTO dto = new OrderDTO();
//			
//			dto.setBookVo(vo.getBookVo());
//			dto.setUserVo(vo.getUserVo());
//			dto.setOrderId(vo.getOrderId());
//			dto.setOrderPrice(vo.getOrderPrice());
//			dto.setOrderDate(vo.getOrderDate());
//			order.add(dto);
//		}
		
		return userMapper.orderList(userNo); 
	}
}
