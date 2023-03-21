<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>ADMIN PAGE</title>
</head>

<body>

	<!-- 대시보드 메인 -->
	<!-- Sale & Revenue Start -->
	<div class="container-fluid pt-4 px-4">
		<div class="row g-4">
			<div class="col-sm-6 col-xl-3">
				<div
					class="bg-secondary rounded d-flex align-items-center justify-content-between p-4">
					<i class="fa fa-chart-line fa-3x text-primary"></i>
					<div class="ms-3">
						<p class="mb-2">오늘의 할인</p>
						<h6 class="mb-0">$1234</h6>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-xl-3">
				<div
					class="bg-secondary rounded d-flex align-items-center justify-content-between p-4">
					<i class="fa fa-chart-bar fa-3x text-primary"></i>
					<div class="ms-3">
						<p class="mb-2">총 할인</p>
						<h6 class="mb-0">$1234</h6>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-xl-3">
				<div
					class="bg-secondary rounded d-flex align-items-center justify-content-between p-4">
					<i class="fa fa-chart-area fa-3x text-primary"></i>
					<div class="ms-3">
						<p class="mb-2">오늘 수익</p>
						<h6 class="mb-0">$1234</h6>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-xl-3">
				<div
					class="bg-secondary rounded d-flex align-items-center justify-content-between p-4">
					<i class="fa fa-chart-pie fa-3x text-primary"></i>
					<div class="ms-3">
						<p class="mb-2">총 수익</p>
						<h6 class="mb-0">$1234</h6>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Sale & Revenue End -->

	<!-- Sales Chart Start -->
	<div class="container-fluid pt-4 px-4">
		<div class="row g-4">
			<div class="col-sm-12 col-xl-6">
				<div class="bg-secondary text-center rounded p-4">
					<div class="d-flex align-items-center justify-content-between mb-4">
						<h6 class="mb-0">년도별 대여</h6>
						<a href="">전체 보기</a>
					</div>
					<canvas id="worldwide-sales"></canvas>
				</div>
			</div>
			<div class="col-sm-12 col-xl-6">
				<div class="bg-secondary text-center rounded p-4">
					<div class="d-flex align-items-center justify-content-between mb-4">
						<h6 class="mb-0">년도별 수익</h6>
						<a href="">전체보기</a>
					</div>
					<canvas id="salse-revenue"></canvas>
				</div>
			</div>
		</div>
	</div>
	<!-- Sales Chart End -->

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