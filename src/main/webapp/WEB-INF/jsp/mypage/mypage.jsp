<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BTONE_LIBRARY_MANAGEMENT</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/common.css'/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/jquery-ui.css'/>" />

<!-- <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> -->
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
	<%@include file="/WEB-INF/jsp/include/header.jsp"%>
	<div class="content" id="tabs1">
		<h1 style="text-align : center">주문 내역</h1>
		<div class="fixedTable">
			<div class="fixedBox">
				<table>
					<thead id="tableHead">
						<tr>
							<th width='20px'></th>
							<th width='50px'>No</th>
							<th width='50px'>ID</th>
							<th width='200px'>책이름</th>
							<th width='200px'>가격</th>
							<th width='100px'>결제일</th>
							<th width='100px'>반납일</th>
						</tr>
					</thead>
					<tbody class="table" id="tableBody">
						<c:forEach var="list" items="${list}" varStatus="status">
							<tr>
								<th width='20px'><input type="checkbox" name="check"
									value="${list.orderId}"></th>
								<th width='50px'>${status.count}</th>
								<th width='50px'>${list.bookVo.goodsId}</th>
								<th width='200px'>${list.bookVo.goodsName}</th>
								<th width='200px'>${list.orderPrice}</th>
								<th width='100px'>${list.orderDate}</th>
								<th width='100px'>${list.orderDate}+7</th>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<%@include file="/WEB-INF/jsp/include/footer.jsp"%>
</body>
</html>