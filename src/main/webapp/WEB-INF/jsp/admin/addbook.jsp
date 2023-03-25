<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script>

	function addbook(){
		
		var vo = {
				goodsId : $("#goodsId").val(),
				goodsName : $("#goodsName").val(),
				goodsPrice : $("#goodsPrice").val(),
				goodsQuantity : $("#goodsQuantity").val(),
				eventId : $("#selectType option:selected").val(),
				goodsAgeLimit : $("#goodsAgeLimit option:selected").val()
		};
		
		var eventId = $("#selectType option:selected").val();
		
		console.log("이벤트 id",eventId);
		console.log(JSON.stringify(vo));
		
		//"eventId" : $("#searchType option:selected").val() //select 태그
		$.ajax({
			url : "/admin/add/book.do",
			/* dataType : "json", */
	        type :   "post",
	        contentType : "application/json",
			data :	JSON.stringify(vo),
			
		    success : function() {  //아래와 같은 데이터 형식으로 가져올거임!
		    	console.log( "도서등록 성공");
		    	alert('도서등록 완료!.');
		    }
			      
		});
	}


</script>
<title>ADMIN PAGE</title>
</head>
<body>

	<div class="container-fluid position-relative d-flex p-0">
		<!-- sidebar include -->
		<%@include file="/WEB-INF/jsp/admin/include/sidebar.jsp"%>
		<!-- Content Start -->
		<div class="content">
			<%@include file="/WEB-INF/jsp/admin/include/header.jsp"%>

			<!-- Form Start -->
			<div class="container-fluid pt-4 px-4">
				<div class="row g-4">
					<!-- <div class="col-sm-12 col-xl-6"> -->
					<div class="col-12">
						<div class="bg-secondary rounded h-100 p-4">
							<h6 class="mb-4">도서 등록</h6>
							<form>
								<div class="mb-3">
									<label for="goodsId" class="form-label">도서ID</label> <input
										type="text" class="form-control" id="goodsId">
								</div>
								<div class="mb-3">
									<label for="goodsName" class="form-label">도서제목</label> <input
										type="text" class="form-control" id="goodsName">
								</div>
								<div class="mb-3">
									<label for="goodsPrice" class="form-label">대여가격</label> <input
										type="text" class="form-control" id="goodsPrice">
								</div>
								<div class="mb-3">
									<label for="goodsPrice" class="form-label">수량</label> <input
										type="text" class="form-control" id="goodsQuantity">
								</div>
								<label for="eventSelect" class="form-label">관람등급</label> 
								<select class="form-select mb-3" aria-label="Default select example" id="goodsAgeLimit">
									<option value="y">19세 이상</option>
									<option value="n">19세 미만</option>
								</select>
								<!-- 할인 정책 적용 -->
								<label for="eventSelect" class="form-label">할인적용</label> 
								<select class="form-select mb-3" aria-label="Default select example" id="selectType">
									<option selected value="">적용안함</option>
									<option value="A">A</option>
									<option value="B">B</option>
									<option value="C">C</option>
									<option value="D">D</option>
								</select>
								<div class="mb-3">
									<label for="formFile" class="form-label">도서이미지</label> <input
										class="form-control bg-dark" type="file" id="formFile">
								</div>

								<button type="button" class="btn btn-primary"
									onclick="addbook();">등록</button>
								<button type="button" class="btn btn-primary">취소</button>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- Form End -->
		</div>
		<!-- Content End -->

		<!-- <!-- Back to Top -->
		<a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i
			class="bi bi-arrow-up"></i></a>
	</div>
	<!-- container-fluid position-relative d-flex p-0 -->

	<!-- JavaScript Libraries -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
	<script src="/lib/admin/chart/chart.min.js"></script>
	<script src="/lib/admin/easing/easing.min.js"></script>
	<script src="/lib/admin/waypoints/waypoints.min.js"></script>
	<script src="/lib/admin/owlcarousel/owl.carousel.min.js"></script>
	<script src="/lib/admin/tempusdominus/js/moment.min.js"></script>
	<script src="/lib/admin/tempusdominus/js/moment-timezone.min.js"></script>
	<script
		src="/lib/admin/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>
	<!-- Template Javascript -->
	<script src="/js/admin/main.js"></script>

</body>

</html>