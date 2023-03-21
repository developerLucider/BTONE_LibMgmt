<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
			
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LOGIN</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/common.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jquery-ui.css'/>" />

<script src="<c:url value='/js/jquery-1.12.4.min.js'/>"></script>
<script src="<c:url value='/js/jquery.form.js'/>"></script>
<script src="<c:url value='/js/jquery-ui.js'/>"></script>

<script>
	$(() => {
		$("#tabs").tabs();
	});
</script>

</head>
<body>
	<%@include file = "/WEB-INF/jsp/include/header.jsp" %>
	<div class="container">

			<div class="" id="tabs1">
					<div class="content">						 
						<div class="login">
							<h3>로그인</h3>	
							<form action ="/login" method="post">				
								아이디: <input type="text" id="userId" name="userId"><br>
								비밀번호:<input type="password" id="userPassword" name="userPassword"><br>
								${msg}
							<button type="submit">로그인</button>
							
							</form>
							<input type="button" value="취소"> 
							
						</div>	
					</div> <!-- content -->
			</div> <!-- tab1 -->
		
	</div> <!-- contabiner -->
	
	<%@include file = "/WEB-INF/jsp/include/footer.jsp" %>

<script>

/* function fn_login(){
	
	let userId = $("#userId").val();
	let userPassword = $("#userPassword").val();
	
	$.ajax(){
		url : "/login",
		type : "post",
		data : {
			"userId" : userId,
			"userPassword" : userPassword
		},
		succeess : function(data){
			console.log("로그이 성공");
		}
	}
}
 */
</script>
</body>
</html>