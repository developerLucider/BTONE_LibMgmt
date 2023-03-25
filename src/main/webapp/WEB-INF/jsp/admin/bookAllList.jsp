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

<script>
//검색
function fn_search(){
	   
	   $.ajax({
			url : "/admin/getAdminSearch.do", //액션역할.
			type : "post", // 보내는 타입 형식
			/* dataType : "json", //데이터 타임 */
			data : {     // 받을 데이터 : 폼데이터
					"keyword" : $("#keyword").val(), //input 검색
					
			}, 
			success : function(data) {  //아레와 같은 데이터 형식으로 가져올거임!
				
		  		if (data.length > 0) {
                 var result = "";
                 
                 $("#tableBody").empty(); //기존 리스트 비우기

                 for ( var i in data) { // 검색결과 리스트 순서대로 출력
                    result += "<tr>"
                    result += "<th>" + (i) + "</th>"
                    result += "<th>" + "이미지 X-" + "</th>"
                    result += "<th id='goodsId'>"
                          + data[i].goodsId + "</th>"
                    result += "<th id='goodsName'>"
                          + data[i].goodsName + "</th>"
                    result += "<th id='goodsPrice'>"
                          + data[i].goodsPrice + "</th>"
                    result += "<th id='goodsPrice'>" + "<a href='/admin/edit/book'>"
                             +"<button class='btn btn-success'>" + "수정" +"</button>" +"</a>" +"</th>"
                    result += "</tr>"
                 }
                 
                 $("#tableBody").append(result); // 검색 결과 table에 적용
                 click_event(); //검색이 끝난후에 생성된 테이블에도 적용이 되어야 하기 때문에  
                   

              } else {
                 alert("검색 결과가 없습니다.");
              }
			},
			error : function(xhr, status, error) {
				alert("에러발생");
			}
	   });
	}


</script>
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
                            <h6 class="mb-4">도서전체목록</h6>
                                <div class="mx-quto input-group-mt-5">
                                	 <div class="mx-quto input-group mt-5">
										<mx-auto>
											<input id="keyword" name="keyword" type="text" class="form-control" placeholder="검색어 입력" aria-label="search" aria-describedby="button-addon2">
										</mx-auto>
										<button class="btn btn-success" type="submit" id="button-search" onclick="fn_search();">검색</button>
									</div>
                                </div>
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">번호</th>
                                            <th scope="col">도서이미지</th>
                                            <th scope="col">도서ID</th>
                                            <th scope="col">도서제목</th>
                                            <th scope="col">도서가격</th>                                     
                                            <th scope="col">도서수량</th>                                     
                                            <th scope="col">버튼</th>
                                        </tr>
                                    </thead>
                                    <tbody class="table" id="tableBody">
                                        <c:forEach var="item" items="${bookList}" varStatus="status">
                                        <tr>
                                            <th scope="row">${status.count}</th>
                                            <td>이미지 X</td>
											<th>${item.goodsId}</th>
											<th>${item.goodsName}</th>
											<th>${item.goodsPrice}</th>
											<th>${item.goodsQuantity}</th>
                                            <td><a href="/admin/edit/book/${item.goodsId }"><button class="btn btn-success">수정</button></a></td>                                          
                                        </tr>
                                        </c:forEach>
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