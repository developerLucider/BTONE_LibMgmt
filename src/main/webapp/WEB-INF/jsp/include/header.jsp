<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<c:url value='/js/jquery-1.12.4.min.js'/>"></script>
<script src="<c:url value='/js/jquery.form.js'/>"></script>
<script src="<c:url value='/js/jquery-ui.js'/>"></script>


<script>

</script>

<body>
<header>
	<div class="header">
		<table width="100%">
			<tbody>
				<tr>
					<th style="width: 200px; text-align: left;">BTONE_LibraryManagement</th>
					<td></td>
					<td>
						<button class="button color_header"  onclick="location.href='/'">HOME</button>
						<button class="button color_header"  onclick="location.href='/regist'">회원가입</button>
						<c:if test ="${sessionScope.loginUser.authVO.userAuth eq 'admin'}">
							<button class="button color_header" onclick="location.href='/admin'">관리자</button>
						</c:if>
						<button class="button color_header"  onclick="location.href='/login'">로그인</button>
						<c:if test = "${loginUser != null}">
							<button class="button color_header"  onclick="location.href='/logout'">로그아웃</button>		
						</c:if>	
						<!--<ul>
							<li><a href="" onclick="">회원가입</a></li>
							<li><a href="" onclick="">관리자</a></li>
							<li><a href="" onclick="">로그인</a></li>
						</ul> -->
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</header>
</body>
</html>


