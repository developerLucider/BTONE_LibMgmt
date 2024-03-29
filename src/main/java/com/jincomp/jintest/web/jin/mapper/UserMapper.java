package com.jincomp.jintest.web.jin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jincomp.jintest.web.jin.vo.OrderVO;
import com.jincomp.jintest.web.jin.vo.PointVO;
import com.jincomp.jintest.web.jin.vo.UserAgeCheckVo;
import com.jincomp.jintest.web.jin.vo.UserAuthVO;
import com.jincomp.jintest.web.jin.vo.UserLogin;
import com.jincomp.jintest.web.jin.vo.UserVO;

@Mapper
public interface UserMapper {

	// 로그인
	public UserVO loginUser(UserVO userVO);
	
	//유저 번호로 정보 찾기
	public UserVO findUserByUserNo(String userNo);
	
	//권한조회
	public UserAuthVO findUserAuth(String userNo);
	
	//권한업데이트
	public int authUpdate(String userNo);

	public List<OrderVO> orderList(int userNo);
	

	
	//성인인증
	public UserVO adult(UserVO userVO);
	
	//인증 변경
	public void changeAdult(int userNo);

	//회원가입 (중복체크)
	int idChk(UserLogin user);

	int join(UserLogin user);

	//포인트 등록
	void getPoint(PointVO pvo);
	//관리자 권한user
	void getAuth(UserAuthVO avo);

	//유저업데이트 유무 
	void getUserAgeCheckVo(UserAgeCheckVo uvo);
	


	
	/** 결제(총 액수 만큼 포인트 차감)
	 * @author mskim
	 * @param pvo
	 */

	void updatePoint(PointVO pvo);

	//마이페이지의 사용자정보 조회
	public UserLogin userPage(int userNo);

	//마이페이지 사용자정보 수정하기
	public int userUpdate(UserLogin userLoginVo);

}
