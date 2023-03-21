<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
			
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BTONE_LIBRARY_MANAGEMENT</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/common.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jquery-ui.css'/>" />
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="<c:url value='/js/jquery-1.12.4.min.js'/>"></script>
<script src="<c:url value='/js/jquery.form.js'/>"></script>
<script src="<c:url value='/js/jquery-ui.js'/>"></script>

<script>
	$(() => {
		$("#tabs").tabs();
	});
	
	
	//회원가입
	function join(){
		
		var reg = /^(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*])[a-z0-9$@!%*#?&]*$/;
		var pw = $("#userPassword").val();
		var pw2 = $("#password2").val();

		console.log(reg.test(pw)); //true 반환

		if(!reg.test(pw)) { //정규식 조건문이 맞지않거나, 
			alert('비밀번호는 8자 이상이며 숫자/영소문자/특수문자를 모두 1개이상 포함해야 합니다.');
			
		}else if(pw != pw2){ //패스워드,패스워드2가 같지않다면 
			alert('비밀번호가 같지 않습니다.');
			
		}else if(reg.test(pw) || pw == pw2){

			$.ajax({
				url : "main/getjoin.do",
				dataType : "json",
		        type :   "post",
			    
				data :{
					"userName" : $("#userName").val(),
					"userId" : $("#userId").val(),
					"userPassword" : $("#userPassword").val(),
					"userAddress" :  $("#Address").val() + $("#userAddress").val()
					},
			    success : function(result) {  //아래와 같은 데이터 형식으로 가져올거임!
			    	if(result < 0){
						alert('가입실패!');
					}
			    	else {
			    		alert('가입성공!');
					}
			    },
			    error : function(result) {
					console.log(result);
				}
			});
		};
     }
	
	
	
</script>
</head>
<body>
	<%@include file = "/WEB-INF/jsp/include/header.jsp" %>
	<div class="container">
		<!-- tabs는 이전 프로젝트 참고해서 해당 탭들 여기에 추가하면 해당 페이지 탭 나눌수 있을겁니다. -->
		<div id="tabs">
			<table width="100%">
					<tbody>
						<tr>
							<th style="width: 200px; text-align: left;">REGIST</th>
							<td>
								<ul>
									<li><a href="#tabs1">가입진행</a></li>
								<!-- 	<li><a href="#tabs2">탭2</a></li>
									<li><a href="#tabs3">탭3</a></li>
									<li><a href="#tabs4">탭4</a></li> -->
								</ul>
							</td>
						</tr>
					</tbody>
			</table>
			<!-- tabs1 -->
			<div class="" id="tabs1">
				<form id="tabs1-frm" name="tabs1-frm" method="post" onSubmit="return false;">
					<div class="content fixedTable">
						<!-- 해당 페이지 기능별로 탭 나눠쓰시면됩니다.
						 header footer가 content 기준으로 사이즈가 잡혀있기때문에 구현할 기능들은 content내에서 구현해주세요-->
						<div class="fixedBox" style="overflow-x: hidden; height: 500px; width: 800px; margin: 0 auto;">
							<table >
								<tr>
									<td class="text">아이디</td>
									<td class="line"><input type="text" name="userId"
										id="userId" placeholder="아이디 입력" /></td>
								</tr>
								<tr>
									<td class="line pt">비밀번호</td>
									<td class="line"><input type="password" id="userPassword"
										name="userPassword" placeholder="비밀번호" /></td>
								</tr>
								<tr>
									<td class="line pt">비밀번호 확인</td>
									<td class="line"><input type="password" name="password2"
										id="password2" placeholder="비밀번호 확인" /></td>
								</tr>
								<tr>
									<td class="line pt">이름</td>
									<td class="line"><input type="text" id="userName"
										name="userName" placeholder="성함" /></td>
								</tr>
								<tr>
									<td class="line pt">주소</td>
									<td class="line">
										<input type="text" id="Address" name="Address" placeholder="주소" />
									</td>
								</tr>
								<tr>
									<td class="line pt">상세주소</td>
									<td class="line">
										<input type="text" id="userAddress" name="userAddress" placeholder="상세주소" />
									</td>
								</tr>
								<script>
									window.onload = function(){
									    document.getElementById("Address").addEventListener("click", function(){ //주소입력칸을 클릭하면
									        //카카오 지도 발생
									        new daum.Postcode({
									            oncomplete: function(data) { //선택시 입력값 세팅
									                document.getElementById("Address").value = data.address; // 주소 넣기
									                document.querySelector("input[name=userAddress]").focus(); //상세입력 포커싱
									            }
									        }).open();
									    });
									}
								</script>
								<tr>
									<td><button class="button btn-secondary">취소</button></td>
									<td><button class="button btn-primary" onclick="join();">회원가입</button></td>
								</tr>
							</table>
						</div>
					</div>
				</form>
			</div>
			<!-- tab2 -->
<!-- 			<div class="" id="tabs2">
			</div>
			tabs3
			<div class="" id="tabs3">
			</div>
			tab4
			<div class="" id="tabs4"> -->
			</div>
		</div>
	</div>
	
	<%@include file = "/WEB-INF/jsp/include/footer.jsp" %>

	<!-- popup  -->
	<div id="popupBox" title="테이블"></div>

</body>
</html>