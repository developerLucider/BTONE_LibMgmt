<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<meta charset="utf-8">
<<<<<<< HEAD
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>ADMIN PAGE</title>
</head>
<body>	
	
	<div class="container-fluid position-relative d-flex p-0">
	<!-- sidebar include -->
	<%@include file="/WEB-INF/jsp/admin/include/sidebar.jsp" %> 
		<!-- Content Start -->
        <div class="content">     
        	<%@include file="/WEB-INF/jsp/admin/include/header.jsp" %>
        	        	                 
            <!-- Form Start -->
            <div class="container-fluid pt-4 px-4">
                <div class="row g-4">
                    <!-- <div class="col-sm-12 col-xl-6"> -->
                    <div class="col-12">
                        <div class="bg-secondary rounded h-100 p-4">
                            <h6 class="mb-4">할인 정책 등록</h6>
                            <form>
                                <div class="mb-3">
                                    <label for="eventId" class="form-label">할인정책명</label>
                                    <input type="text" class="form-control" id="eventId">
                                </div>                                                    
                                <!-- 할인 정책 적용 -->
                                <label for="eventSelect" class="form-label">할인정책</label>
		                            <select class="form-select mb-3" aria-label="Default select example">		                            
		                                <option value="">고정할인가</option>
		                                <option value="">%할인</option>		                           
		                            </select>
		                        
		                        <!-- 추후에 달력으로 바꾸는게 좋아보임. -->    
		                        <div class="mb-3">
                                    <label for="eventStart" class="form-label">할인시작일</label>
                                    <input type="text" class="form-control" id="eventStart" placeholder="형식(YYYY-mm-DD)">
                                </div>
                                <div class="mb-3">
                                    <label for="eventEnd" class="form-label">할인마지막일</label>
                                    <input type="text" class="form-control" id="eventEnd" placeholder="형식(YYYY-mm-DD)">
                                </div>      
		                         		                                                      		                                                      
                                <button type="button" class="btn btn-primary">등록</button>
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
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
    </div> <!-- container-fluid position-relative d-flex p-0 -->  
    
   		<!-- JavaScript Libraries -->
		<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
		<script src="/lib/admin/chart/chart.min.js"></script>
		<script src="/lib/admin/easing/easing.min.js"></script>
		<script src="/lib/admin/waypoints/waypoints.min.js"></script>
		<script src="/lib/admin/owlcarousel/owl.carousel.min.js"></script>
		<script src="/lib/admin/tempusdominus/js/moment.min.js"></script>
		<script src="/lib/admin/tempusdominus/js/moment-timezone.min.js"></script>
		<script src="/lib/admin/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>
		
		<!--  -->
		<!-- Template Javascript -->
		<script src="/js/admin/main.js"></script> 
    
=======
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>ADMIN PAGE</title>
<script>
	function fn_addEvent() { // 등록버튼 눌렀을때 
		var sale = $("input[name=sale]:checked").val();
	
	console.log("할인 종류 : ",sale);

		var info = {
			"eventId" : $("#eventId").val(),
			[sale] : $("#sale").val(),      //키값 동적 할당할때 []로 감싸기 
			"rateStrDay" : $("#eventStart").val(),
			"rateEndDay" : $("#eventEnd").val()
		}
		console.log("등록 이벤트 정보 : ", info);
		
		$.ajax({
			url : "add/addEvent.do",
			type : "POST",
			contentType :"application/json",
			dataType:"text",
			data: JSON.stringify(info),
			success :function(data){
				alert(data);
				window.location.href="/admin/event/add"
			},
			error : function(error){
				alert(error);
			}
		});
	};
</script>
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
							<h6 class="mb-4">할인 정책 등록</h6>

							<form id="eventInfo" name="eventInfo" method="post">
								<div class="mb-3">
									<label for="eventId" class="form-label">할인정책명</label> <input
										type="text" class="form-control" id="eventId">
								</div>
								<!-- 할인 정책 적용 -->
								<label for="eventSelect" class="form-label">할인정책</label> <br>
								<input type="radio" name="sale" value="fixDiscount">고정 할인
								 &nbsp;&nbsp;&nbsp; 
								<input type="radio" name="sale" value="rate">비율 할인 
								<input type="text" class="form-control" id="sale">

								<!-- 추후에 달력으로 바꾸는게 좋아보임. -->
								<div class="mb-3">
									<label for="eventStart" class="form-label">할인시작일</label> <input
										type="text" class="form-control" id="eventStart"
										placeholder="형식(YYYY-mm-DD)">
								</div>
								<div class="mb-3">
									<label for="eventEnd" class="form-label">할인마지막일</label> <input
										type="text" class="form-control" id="eventEnd"
										placeholder="형식(YYYY-mm-DD)">
								</div>
							</form>
							<button type="button" class="btn btn-primary"
								onclick="fn_addEvent();" id="submit">등록</button>
							<button type="button" class="btn btn-primary" id="reset">취소</button>

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

	<!--  -->
	<!-- Template Javascript -->
	<script src="/js/admin/main.js"></script>

>>>>>>> branch '천소진' of https://github.com/developerLucider/BTONE_LibMgmt
</body>

</html>