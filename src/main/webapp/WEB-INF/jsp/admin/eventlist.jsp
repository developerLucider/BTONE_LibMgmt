<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>ADMIN BOOKLIST</title>

<script src="<c:url value='/js/jquery-1.12.4.min.js'/>"></script>
<script src="<c:url value='/js/jquery.form.js'/>"></script>
<script src="<c:url value='/js/jquery-ui.js'/>"></script>

<script>
	$(() => {
		getEvent();
	});
	
	function getEvent() {
		$.ajax({
			url : "list/getEvent.do",
			type : "post",
			dataType : "json",
			data : { },
			success : function(data) {
				var list = data;

				var htmlBody = "";
				$("#tableBody").empty();
				for (i = 0; i < list.length; i++) {
					var item = list[i];
					htmlBody += "<tr>";
					htmlBody += "<th>" + (i + 1) + "</th>";
					htmlBody += "<th>" + item["eventId"] + "</th>";
					htmlBody += "<th>" + item["fixDiscount"] + "</th>";
					htmlBody += "<th>" + item["rate"] + "</th>";
					htmlBody += "<th>" + item["rateStrDay"] + "</th>";
					htmlBody += "<th>" + item["rateEndDay"] + "</th>";
					htmlBody += "</tr>";
				}

				$("#tableBody").html(htmlBody);
				
					},
			error : function(xhr, status, error) {
				alert("에러발생");
			}
		});
	}
</script>
</head>

<body>
	<!-- 전체 레이아웃 -->
    <div class="container-fluid position-relative d-flex p-0">
		<!-- sidebar include -->
		<%@include file="/WEB-INF/jsp/admin/include/sidebar.jsp" %> 
			<!-- Content Start -->
        <div class="content">     
        <%@include file="/WEB-INF/jsp/admin/include/header.jsp" %>  
            <!-- Table Start -->
            <div class="container-fluid pt-4 px-4">
                <div class="row g-4">
                    <div class="col-12">
                        <div class="bg-secondary rounded h-100 p-4">
                            <h6 class="mb-4">할인정책목록</h6>
                            <!-- 검색 이나 라디오버튼 같은 걸로 정책 및 날짜에 맞게 조회 되게 UI만들어 줄 수 있음. -->
                            <div class="table-responsive">
                                <table class="table">
                                    <thead id="tableHead">
                                        <tr>
                                            <th scope="col">번호</th>
                                            <th scope="col">정책명</th>
                                            <th scope="col">고정할인가</th>
                                            <th scope="col">할인퍼센트</th>
                                            <th scope="col">할인시작일</th>                                     
                                            <th scope="col">할인종료일</th>
                                        </tr>
                                    </thead>
                                    <tbody id="tableBody">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div> <!-- Table End -->
        </div><!-- Content layout End -->
  
        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
    </div>

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
		<!-- Template Javascript -->
		<script src="/js/admin/main.js"></script>
</body>

</html>