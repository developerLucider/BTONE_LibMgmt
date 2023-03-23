<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<<<<<<< HEAD
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script>
//검색
function fn_search(){
	   
	   $.ajax({
			url : "/admin/getAdminUserList.do", //액션역할.
			type : "post", // 보내는 타입 형식
			/* dataType : "json", //데이터 타임 */
			data : {     // 받을 데이터 : 폼데이터
					"userKeyWord" : $("#userKeyWord").val(), //input 검색
					
			}, 
			success : function(data) {  //아레와 같은 데이터 형식으로 가져올거임!
				
		  		if (data.length > 0) {
                 var result = "";
                 
                 $("#tableBody").empty(); //기존 리스트 비우기

                 for ( var i in data) { // 검색결과 리스트 순서대로 출력
                    result += "<tr>"
                    result += "<th id='userNo'>" + data[i].userNo + "</th>"
                    result += "<th id='userName'>" + data[i].userName + "</th>"
                    result += "<th id='userId'>" + data[i].userId + "</th>"
                    result += "<th id='userAddress'>" + data[i].userAddress + "</th>"
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

<title>ADMIN USERLIST</title>
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
                            <h6 class="mb-4">유저목록</h6>
                                <div class="mx-quto input-group-mt-5">
                                	 <div class="mx-quto input-group mt-5">
                                	 <mx-auto>
											<input id="userKeyWord" name="userKeyWord" type="text" class="form-control" placeholder="검색어 입력" aria-label="search" aria-describedby="button-addon2">
									</mx-auto>
										<button class="btn btn-success" type="submit" id="button-search" onclick="fn_search();">검색</button>
									</div>
									</div>
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">번호</th>
                                            <th scope="col">이름</th>
                                            <th scope="col">아이디</th>
                                            <th scope="col">주소</th>
                                        </tr>
                                    </thead>
                                    <tbody class="table" id="tableBody">
                                        <c:forEach var="item" items="${getUserListt}" varStatus="status">
                                        <tr>
											<th>${item.userNo}</th>
											<th>${item.userName}</th>
											<th>${item.userId}</th>
											<th>${item.userAddress}</th>
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
  
  <!-- 테스트입니다 -->
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