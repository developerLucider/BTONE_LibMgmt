
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>ADMIN BOOKLIST</title>

<script src="<c:url value='/js/jquery-1.12.4.min.js'/>"></script>
<script src="<c:url value='/js/jquery.form.js'/>"></script>
<script src="<c:url value='/js/jquery-ui.js'/>"></script>

<script>
	$(document).ready(function() { // 화면이 켜지면 기본적으로 셋팅되는효과들을 정의

		click_event(); // tr 클릭 이벤트
		btnActive(); // 수정버튼 활성화 함수 				
	});
	function click_event() { //브라우저에서 항상 적용되는 클릭 이벤트 함수 

		$("#tableBody tr").click(
				function() { // 행사 목록 테이블의 행에 대한 클릭이벤트
					var tr = $(this); // 클릭한 행
					var td = tr.children(); //클릭한 행의 td들

					$("#tr_eventId").prop("value", td.eq(2).text());   // 체크박스를 제외한 데이터를 하나씩 맞게 뿌려주기.
					$("#tr_rateStrDay").prop("value", td.eq(5).text());
					$("#tr_rateEndDay").prop("value", td.eq(6).text());
					
					if(td.eq(3).text() == 0){  // 고정할인가가 0인경우에
						$(":radio[name=tr_sale][value=" + td.eq(4).attr("id") + "]")   //td.eq(4)의 id값(rate)를 추출해서
						.prop("checked", true);    // value가 rate인 라디오버튼에 체크되도록
						$("#input").prop("value", td.eq(4).text());  //text(할인율)값은 input칸에  
						
					}else if(td.eq(4).text() == 0){
						$(":radio[name=tr_sale][value=" + td.eq(3).attr("id") + "]")
						.prop("checked", true); 
						$("#input").prop("value", td.eq(3).text());
					}					
					$("#updateBtn").prop("disabled", true); //행 클릭할때마다 버튼 비활성화 기본값 설정.
				});
		
	}

	function btnActive() { // 버튼 활성화 함수 
		$("#update :input").on("propertychange change keyup paste input",
				function() {
					$("#updateBtn").attr("disabled", false);
				});
	}
	
	function fn_update(){
		var radio = $("input:radio[name=tr_sale]:checked").val();  // 라디오버튼 value값
		var edit_data= {
				"eventId" : $("#tr_eventId").val(),
				"rateStrDay" : $("#tr_rateStrDay").val(),
				"rateEndDay" : $("#tr_rateEndDay").val(),
				[radio] : $("#input").val()
		}
		
		console.log("data :", JSON.stringify(edit_data));
		
		$.ajax({
			
			url : "list/update.do",
			type :"POST",
			dataType:"text",
			contentType : "application/json",
			data : JSON.stringify(edit_data),
			success : function(data){
				alert(data);
				location.reload(); 
			},
			error : function(e){
				alert(e);
			}			
		});
	}
	function fn_delete() { // 삭제 기능 				

		var list = []; // 체크 리스트 담을 빈배열
		var check = $("input[name=check]:checked"); // name=check인 체크박스중 체크된 항목들
		check.each(function(i) {
			var empNo = $(this).val(); //체크된 항목의 value값
			list.push(empNo); // 빈 배열에 담기
			console.log("list", list);
		});

		if (list.length) {
			var answer = confirm("정말 삭제하시겠습니까?"); // 삭제 확인

			if (answer === true) { //"yes"인경우

				$.ajax({
					url : "list/delete.do", //액션역할.
					type : "POST", // 보내는 타입 형식
					dataType : "text", //데이터 타입
					data : {
						"list" : list
					},
					success : function(data) { 
						alert(data);
						location.reload(); // 삭제후 페이지 리로딩
					},
					error : function(error) {
						alert(error);
					}
				});
			} else {
				location.reload(); // "No"일 경우
			}
		} else {
			alert("삭제할 항목이 없습니다.");
		}
	}

</script>
</head>

<body>
	<!-- 전체 레이아웃 -->
	<div class="container-fluid position-relative d-flex p-0">
		<!-- sidebar include -->
		<%@include file="/WEB-INF/jsp/admin/include/sidebar.jsp"%>
		<!-- Content Start -->
		<div class="content">
			<%@include file="/WEB-INF/jsp/admin/include/header.jsp"%>
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
											<th width='20px' scope="col"></th>
											<th width='100px' scope="col">번호</th>
											<th width='200px' scope="col">정책명</th>
											<th width='200px' scope="col">고정할인가</th>
											<th width='200px' scope="col">할인퍼센트</th>
											<th width='250px' scope="col">할인시작일</th>
											<th width='250px' scope="col">할인종료일</th>
										</tr>
									</thead>
									<tbody id="tableBody">
										<c:forEach var="list" items="${list}" varStatus="status">
											<tr>
												<!-- 도서목록 -->
												<td width='20px'><input type="checkbox" name="check" value="${list.eventId}"></td>
												<td width='100px'>${status.count}</td>
												<td width='200px' id="eventId">${list.eventId}</td>
												<td width='200px' id="fixDiscount">${list.fixDiscount}</td>
												<td width='200px' id="rate">${list.rate }</td>
												<td width='250px' id="rateStrDay">${list.rateStrDay }</td>
												<td width='250px' id="rateEndDay">${list.rateEndDay }</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<button class="btn btn-secondary"
									style="float: right; padding: 6px 12px"onclick="fn_delete();">선택 삭제</button>
							</div>
						</div>
						<div class="update" id="update" style="margin: 20px">
							<label><b>이벤트명</b></label><input type="text" id="tr_eventId" style="width: 70px" disabled>&nbsp;&nbsp; 
							<label><b>시작날짜</b>	</label><input type="text" id="tr_rateStrDay" style="width: 120px">&nbsp;&nbsp;
							<label><b>종료날짜</b></label><input type="text" id="tr_rateEndDay" style="width: 120px">&nbsp;&nbsp;							
							<label><b>분류</b></label> 
							<input type="radio" name="tr_sale" value="fixDiscount"> 고정할인
							<input type="radio" name="tr_sale" value="rate"> 비율할인
							 &nbsp;&nbsp;
							<input type="text" id="input" style="width: 120px">&nbsp;&nbsp;
							<button onclick="fn_update();" id="updateBtn" disabled="disabled">수정</button>
						</div>
					</div>
				</div>
			</div>
			<!-- Table End -->
		</div>
		<!-- Content layout End -->

		<!-- Back to Top -->
		<a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i
			class="bi bi-arrow-up"></i></a>
	</div>

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
>>>>>>> refs/remotes/origin/master
</body>

</html>