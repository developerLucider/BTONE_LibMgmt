package com.jincomp.jintest.web.jin.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.common.Base64Utils;
import com.common.Utils;
import com.jincomp.jintest.web.jin.dto.OrderDTO;
import com.jincomp.jintest.web.jin.mapper.UserMapper;
import com.jincomp.jintest.web.jin.vo.OrderVO;
import com.jincomp.jintest.web.jin.vo.PointVO;
import com.jincomp.jintest.web.jin.vo.UserAgeCheckVo;
import com.jincomp.jintest.web.jin.vo.UserAuthVO;
import com.jincomp.jintest.web.jin.vo.UserLogin;
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
	 	logger.debug("------------------loginUser : {}", loginUser);
	    
	    if(loginUser != null) {	 	    			
	      // 디비 값 
		  String oldId = loginUser.getUserId();
		  String oldPw = loginUser.getUserPassword();

		  logger.debug("디비에 저장된 아이디{}", oldId);
		  logger.debug("디비에 저장된 암호화 비밀번호{}", oldPw);   //인코딩된 비밀번호 안나옴.

	      
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
		
		List<OrderDTO> order = new ArrayList<>();
		List<OrderVO> list = userMapper.orderList(userNo);  // 현재 회원의 주문내역 리스트 
		
		for(OrderVO vo :list) {
			
			OrderDTO dto = new OrderDTO();
			
			dto.setBookVo(vo.getBookVo());
			dto.setUserVo(vo.getUserVo());
			dto.setOrderId(vo.getOrderId());
			dto.setOrderPrice(vo.getOrderPrice());
			dto.setOrderDate(vo.getOrderDate());
			order.add(dto);
		}
		
		return userMapper.orderList(userNo); 
	}
	
	
	//회원가입
		public int join(UserLogin user) {
		logger.debug("user : {}", user);
		
		int joinck = 0;
		//중복된 아이디가 없으면
		if (idChk(user)) { //id가  true이면

			String pass = user.getUserPassword(); // user에 가져온 비번
			//비번 없을떄 예외처리 하기.
			String pwd = Base64Utils.base64Encoder(pass);
//
			user.setUserPassword(pwd);
			logger.debug("user : {}", user);
			
			joinck = userMapper.join(user);  //회원가입 mapper
			
			//포인트 삽입
			int point = 500000;  //포인트 금액 설정
			PointVO pvo = new PointVO(); 
			pvo.setPoint(point); //포인트 넣어주기
			
			userMapper.getPoint(pvo);    //포인트 mapper
			
			logger.debug("pointVO : {}", pvo);
			
			//관리자 권한 생성
			String auth = "user";
			
			UserAuthVO avo = new UserAuthVO();
			avo.setUserAuth(auth);
			
			userMapper.getAuth(avo);  //관리자권한 mapper
			
			logger.debug("UserAuthVO : {}", avo);
			
			//유저 성인 인증 체크 유무
			UserAgeCheckVo uvo = new UserAgeCheckVo();
			userMapper.getUserAgeCheckVo(uvo); 
			
		}	
		
		return joinck;
	}
	
	//아이디 중복체크
	public Boolean idChk(UserLogin user) {
		int result = userMapper.idChk(user);
		
		//중복된 아이디가 없음
		if(result == 0) {
			return true;
		}
		return false;
	}

	
	//성인 인증 
	public UserVO adult(UserVO userVO, HttpServletRequest request, String userRegNo1) {
		
		logger.debug("{}", "성인인증 서비스 진입");
		
		HttpSession httpSession = request.getSession();
		//로그인 세션에서 No 값을 가져옴
		String sNum = (String) httpSession.getAttribute("userNo");
		//주민번호 뒷자리
		String backNum = userRegNo1;
		
		//주민번호 앞 뒷자리 합친 것
		String regNo = userVO.getUserRegNo()+backNum;
		
		userVO.setUserRegNo(regNo);
		userVO.setUserNo(sNum);
		
		logger.debug("로그인 세션 중 userNo : {}", sNum);
		logger.debug("로그인 세션 중 userNo : {}", Integer.parseInt(sNum));
		logger.debug("userVO : {}", userVO);

		//내가 입력한 값 
		String uName = userVO.getUserName();
		String uNum1 = userVO.getUserRegNo();
		
		logger.debug("입력받은 이름 : {}", uName);
		logger.debug("입력받은 주민번호 : {}",uNum1);
		logger.debug("입력한 userRegNo1 : {}", userRegNo1);
		
		//이게 DB에서 이름 주민번호에 해당하는 정보
		UserVO adultUser = userMapper.adult(userVO);
		
		
		//여 아래 num을 쓸거에용. -> DB에서 가져온 데이터입니다.
		String dNum = adultUser.getUserNo();
		String dName = adultUser.getUserName();
		String dCheck = adultUser.getUserAgeCheckYn();
		
//		UserVO result;
		
		logger.debug("DB에서 받은 전체 데이터 : {}", adultUser);

		logger.debug("DB에서 받은 번호 : {}",dNum);
		logger.debug("DB에서 받은 이름 : {}",dName);
		logger.debug("DB에서 받은 현재 인증상태 : {}",dCheck);
		
			
		if(adultUser != null) {
	         
	         if(sNum.equals(dNum)) {// dbnum와 로그인 세션에서 No가 같을 때
	            
	            if(Utils.adultCertification(userVO.getUserRegNo(), backNum) >= 19 ) { //19세 이상이면 (regNo의 앞 2자리 - 이번년도) = 19 이상일때
	               
	               userMapper.changeAdult(Integer.parseInt(sNum));
	               
	            }else { //19세 미만이면 
	               
	               return null;
	            }
	            
	            //추가해야하는 것 : 빈칸일때, 틀린 값일때(빈칸과 같을수있다), 
	            //이미 인증이 되었을 경우 dCheck.equals("y") 일단 이정도? 
	         }
	         
	      } 
	      
	      return adultUser;
	   }

	
	//사용자 정보 조회
	public UserLogin userPage( int userNo ) {
		logger.debug("userNo : {}", userNo);
		
		return userMapper.userPage(userNo);
	}

	//회원정보 수정
	public int userUpdate(UserLogin userLoginVo, HttpServletRequest request) {//throws BtoneExeption
		logger.debug("수정Vo : {}", userLoginVo);
		HttpSession httpSession = request.getSession();
		//로그인 세션에서 No 값을 가져옴
		String userNo = (String) httpSession.getAttribute("userNum");
		userLoginVo.setUserNo(Integer.parseInt(userNo)); //로그인VO와 세션vo 바꿔주기
		
		// 로그인VO에 가져온 비번
		String pass = userLoginVo.getUserPassword(); 
		String pwd = Base64Utils.base64Encoder(pass);
		userLoginVo.setUserPassword(pwd);

		logger.debug("비밀번호 : {}", pass);
		logger.debug("암호화 비번 : {}", pwd);

		
		int user = userMapper.userUpdate(userLoginVo);
		
		return user;
	}
	

		
		
}
