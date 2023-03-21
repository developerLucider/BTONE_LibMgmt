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


<!-- Favicon -->
<link href="/img/favicon.ico" rel="icon">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Roboto:wght@500;700&display=swap"
	rel="stylesheet">

<!-- Icon Font Stylesheet -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
	rel="stylesheet">

<!-- Libraries Stylesheet -->
<link href="/lib/admin/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
<link href="/lib/admin/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />


<!-- Customized Bootstrap Stylesheet -->
<link rel="stylesheet" href="<c:url value= '/css/admin/bootstrap.min.css'/>"/>
<!-- 어드민 페이지 css 공통 -->
<link rel="stylesheet" type="text/css" href="<c:url value='/css/admin/style.css'/>" />
<!-- 아이콘 라이브러리 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />

</head>

<body>

		<!-- 리로드 아이콘 Start -->
		<div id="spinner" class="show bg-dark position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
			<div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
				<span class="sr-only">Loading...</span>
			</div>
		</div>
		<!-- 리로드 아이콘 End -->

	<!-- 사이드바 Start -->
		<div class="sidebar pe-4 pb-3">
			<nav class="navbar bg-secondary navbar-dark">
				<a href="/admin/" class="navbar-brand mx-4 mb-3">
					<h3 class="text-primary"><i class="fa fa-user-edit me-2"></i>BTONE</h3>
				</a>
				<div class="d-flex align-items-center ms-4 mb-4">
					<div class="position-relative">
						<img class="rounded-circle" src="/img/user.jpg" alt=""
							style="width: 40px; height: 40px;">
						<div
							class="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
					</div>
					<div class="ms-3">
						<h6 class="mb-0">박상훈</h6>
						<span>관리자</span>
					</div>
				</div>
				<!-- d-flex align-items-center ms-4 mb-4 -->

				<!-- 카테고리 -->
				<div class="navbar-nav w-100">
					<a href="/admin/" class="nav-item nav-link active"><i class="fa fa-tachometer-alt me-2"></i>대시보드</a>
					<!-- 도서 관리 -->
					<div class="nav-item dropdown">
						<a href="#" class="nav-link dropdown-toggle"
							data-bs-toggle="dropdown"><i class="fa fa-laptop me-2"></i>도서관리</a>
						<div class="dropdown-menu bg-transparent border-0">
							<a href="/admin/add/book" class="dropdown-item">도서등록</a> 
							<a href="/admin/list/books" class="dropdown-item">도서전체조회</a> 
							<a href="/admin/list/rent" class="dropdown-item">대여현황</a>
						</div>
					</div>
					<!-- 이벤트 관리 -->
					<div class="nav-item dropdown">
						<a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
						<i class="fa fa-laptop me-2"></i>이벤트관리</a>
						<div class="dropdown-menu bg-transparent border-0">
							<a href="/admin/add/event" class="dropdown-item">이벤트등록</a> 
							<a href="/admin/list/event" class="dropdown-item">이벤트정책조회</a>
						</div>
					</div>

					<!-- 유저 관리 -->
					<div class="nav-item dropdown">
						<a href="/admin/" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
						<i class="fa fa-laptop me-2"></i>유저관리</a>
						<div class="dropdown-menu bg-transparent border-0">
							<a href="/admin/list/user" class="dropdown-item">유저정보</a>							
						</div>
					</div>
				</div>
				<!-- 카테고리 end -->
			</nav>
		</div>
		<!-- 사이드바 end (include 빼기) -->

<!-- JavaScript Libraries -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<!-- 	<script src="/lib/admin/chart/chart.min.js"></script> -->
	<script src="/lib/admin/easing/easing.min.js"></script>
	<script src="/lib/admin/waypoints/waypoints.min.js"></script>
	<script src="/lib/admin/owlcarousel/owl.carousel.min.js"></script>
	<script src="/lib/admin/tempusdominus/js/moment.min.js"></script>
	<script src="/lib/admin/tempusdominus/js/moment-timezone.min.js"></script>
	<script src="/lib/admin/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>
=======
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>ADMIN PAGE</title>


<!-- Favicon -->
<link href="/img/favicon.ico" rel="icon">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Roboto:wght@500;700&display=swap"
	rel="stylesheet">

<!-- Icon Font Stylesheet -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
	rel="stylesheet">

<!-- Libraries Stylesheet -->
<link href="/lib/admin/owlcarousel/assets/owl.carousel.min.css"
	rel="stylesheet">
<link
	href="/lib/admin/tempusdominus/css/tempusdominus-bootstrap-4.min.css"
	rel="stylesheet" />


<!-- Customized Bootstrap Stylesheet -->
<link rel="stylesheet"
	href="<c:url value= '/css/admin/bootstrap.min.css'/>" />
<!-- 어드민 페이지 css 공통 -->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/admin/style.css'/>" />
<!-- 아이콘 라이브러리 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />

</head>

<body>

	<!-- 리로드 아이콘 Start -->
	<div id="spinner"
		class="show bg-dark position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
		<div class="spinner-border text-primary"
			style="width: 3rem; height: 3rem;" role="status">
			<span class="sr-only">Loading...</span>
		</div>
	</div>
	<!-- 리로드 아이콘 End -->

	<!-- 사이드바 Start -->
	<div class="sidebar pe-4 pb-3">
		<nav class="navbar bg-secondary navbar-dark">
			<a href="/admin/" class="navbar-brand mx-4 mb-3">
				<h3 class="text-primary">
					<i class="fa fa-user-edit me-2"></i>BTONE
				</h3>
			</a>
			<div class="d-flex align-items-center ms-4 mb-4">
				<div class="position-relative">
					<img class="rounded-circle" src="/img/user.jpg" alt=""
						style="width: 40px; height: 40px;">
					<div
						class="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
				</div>
				<div class="ms-3">
					<h6 class="mb-0">박상훈</h6>
					<span>관리자</span>
				</div>
			</div>
			<!-- d-flex align-items-center ms-4 mb-4 -->

			<!-- 카테고리 -->
			<div class="navbar-nav w-100">
				<a href="/admin/" class="nav-item nav-link active"><i
					class="fa fa-tachometer-alt me-2"></i>대시보드</a>
				<!-- 도서 관리 -->
				<div class="nav-item dropdown">
					<a href="#" class="nav-link dropdown-toggle"
						data-bs-toggle="dropdown"><i class="fa fa-laptop me-2"></i>도서관리</a>
					<div class="dropdown-menu bg-transparent border-0">
						<a href="/admin/list/books" class="dropdown-item">도서전체조회</a> <a
							href="/admin/add/book" class="dropdown-item">도서등록</a> <a
							href="/admin/list/rent" class="dropdown-item">대여현황</a>
					</div>
				</div>
				<!-- 이벤트 관리 -->
				<div class="nav-item dropdown">
					<a href="#" class="nav-link dropdown-toggle"
						data-bs-toggle="dropdown"> <i class="fa fa-laptop me-2"></i>이벤트관리
					</a>
					<div class="dropdown-menu bg-transparent border-0">
						<a href="/admin/event/list" class="dropdown-item">이벤트정책조회</a> <a
							href="/admin/event/add" class="dropdown-item">이벤트등록</a>
					</div>
				</div>
				<!-- 유저 관리 -->
				<div class="nav-item dropdown">
					<a href="/admin/" class="nav-link dropdown-toggle"
						data-bs-toggle="dropdown"> <i class="fa fa-laptop me-2"></i>유저관리
					</a>
					<div class="dropdown-menu bg-transparent border-0">
						<a href="/admin/list/user" class="dropdown-item">유저정보</a>
					</div>
				</div>

			</div>
			<!-- 카테고리 end -->
		</nav>
	</div>
	<!-- 사이드바 end (include 빼기) -->

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
>>>>>>> branch '천소진' of https://github.com/developerLucider/BTONE_LibMgmt
	<!-- Template Javascript -->
	<script src="/js/admin/main.js"></script>
</body>
</html>