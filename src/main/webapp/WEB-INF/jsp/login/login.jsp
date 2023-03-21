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
		<!-- tabs는 이전 프로젝트 참고해서 해당 탭들 여기에 추가하면 해당 페이지 탭 나눌수 있을겁니다. -->
		<div id="tabs">
			<table width="100%">
					<tbody>
						<tr>
							<th style="width: 200px; text-align: left;">LOGIN</th>
							<td>
								<ul>
									<li><a href="#tabs1">탭1</a></li>
									<li><a href="#tabs2">탭2</a></li>
									<li><a href="#tabs3">탭3</a></li>
									<li><a href="#tabs4">탭4</a></li>
								</ul>
							</td>
						</tr>
					</tbody>
			</table>
			<!-- tabs1 -->
			<div class="content" id="tabs1">
				<form id="tabs1-frm" name="tabs1-frm" method="post" onSubmit="return false;">
					
				</form>
			</div>
			<!-- tab2 -->
			<div class="content" id="tabs2">
			</div>
			<!-- tabs3 -->
			<div class="content" id="tabs3">
			</div>
			<!-- tab4 -->
			<div class="content" id="tabs4">
			</div>
		</div>
	</div>
	
	<%@include file = "/WEB-INF/jsp/include/footer.jsp" %>

	<!-- popup  -->
	<div id="popupBox" title="테이블"></div>

</body>
</html>