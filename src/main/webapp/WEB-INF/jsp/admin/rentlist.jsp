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
                            <h6 class="mb-4">대여현황목록</h6>
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">번호</th>
                                            <th scope="col">유저이름</th>
                                            <th scope="col">도서제목</th>
                                            <th scope="col">대여시작일</th>
                                            <th scope="col">대여반납일</th>                                     
                                            <th scope="col">수량</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <th scope="row">1</th>
                                            <td>박상훈</td>
                                            <td>책1</td>
                                            <td>2023-03-10</td>
                                            <td>2023-01-17</td>
                                            <td>1</td>                                          
                                        </tr>
                                        <tr>
                                            <th scope="row">2</th>
                                            <td>신승현</td>
                                            <td>책2</td>
                                            <td>2023-03-10</td>
                                            <td>2023-01-17</td>
                                            <td>2</td>                                            
                                        </tr>
                                        <tr>
                                            <th scope="row">3</th>
                                            <td>천소진</td>
                                            <td>책3</td>
                                            <td>2023-03-10</td>
                                            <td>2023-03-10</td>
                                            <td>1</td>                                         
                                        </tr>
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