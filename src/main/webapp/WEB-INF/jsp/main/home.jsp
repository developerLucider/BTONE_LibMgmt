<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
         
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BTONE_LIBRARY_MANAGEMENT</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/common.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jquery-ui.css'/>" />

<script src="<c:url value='/js/jquery-1.12.4.min.js'/>"></script>
<script src="<c:url value='/js/jquery.form.js'/>"></script>
<script src="<c:url value='/js/jquery-ui.js'/>"></script>

<script>
   $(() => {
      $("#tabs").tabs();
   });
   
   //검색
   function fn_search(){
	   
	   $.ajax({
			url : "main/getSearch.do", //액션역할.
			type : "post", // 보내는 타입 형식
			/* dataType : "json", //데이터 타임 */
			data : {     // 받을 데이터 : 폼데이터
					"search" : $("#search").val(), //input 검색
					"searchType" : $("#searchType option:selected").val()
			}, //select 옵션
				
			success : function(data) {  //아레와 같은 데이터 형식으로 가져올거임!
				
		  		if (data.length > 0) {
                    var result = "";
                    $("#tableBody").empty(); //기존 리스트 비우기

                    for ( var i in data) { // 검색결과 리스트 순서대로 출력
                       result += "<tr>"
                       result += "<th width='20px'><input type='checkbox' name='checked' value ="+ data[i].goodsId +"></th>"
                       result += "<th width='30px'>" + (i + 1)
                             + "</th>"
                       result += "<th width='30px' id='goodsId'>"
                             + data[i].goodsId + "</th>"
                       result += "<th width='50px' id='goodsName'>"
                             + data[i].goodsName + "</th>"
                       result += "<th width='70px' id='goodsPrice'>"
                             + data[i].goodsPrice + "</th>"
                       result += "<th width='70px' id='goodsPrice'>"
                                 + data[i].goodsPrice + "</th>"
                       result += "<th width='60px' id='rateStrDay'>"
                                 + data[i].goodsPrice + "</th>"
                       result += "<th width='60px' id='rateEndDay'>"
                           + data[i].goodsPrice + "</th>"
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
   
   //대여하기
   function lental(){
	   var arr = new Array(); //체크값의 goodsId를 배열에 담고 배열을 컨트롤러로 넘겨주기.
	   var list = $("input[name='checked']");
	   for (var i = 0; i < list.length; i++) {
			if (list[i].checked) {
				arr.push(list[i].value);
				console.log(arr);
			}
		}

		if (arr.length == 0) {
			alert("선택없음");

		} else {
		   	$.ajax({
		   		url : "main/lental.do",
				dataType : "json",
				type : "post",
				async : true, //동기: false, 비동기: ture
				data : {
					"arr" : arr
				//input에 체크박스 
				},
				success : function(data) { //아래와 같은 데이터 형식으로 가져올거임!
					console.log(arr + "대여완료!");
				}
		   });
		}
   }
   
</script>
</head>
<body>
   <%@include file = "/WEB-INF/jsp/include/header.jsp" %>
   <div class="container">
      <div id="tabs">
         <table width="100%">
               <tbody>
                  <tr>
                     <th style="width: 200px; text-align: left;">HOME</th>
                     <td>
                        <ul>
                           <li><a href="#tabs1">전체목록</a></li>
                           <li><a href="#tabs2">대여가능</a></li>
                           <!-- <li><a href="#tabs3">대여하기</a></li> -->

                        </ul>
                     </td>
                  </tr>
               </tbody>
         </table>
         <!-- tabs1 -->
			<div class="" id="tabs1">
				<form method="post" onSubmit="return false;">
					<div class="content">
						<div class="search_select">
							<select id="searchType" name="searchType">
								<option value="">--선택--</option>
								<option value="GOODS_ID">책아이디</option>
								<option value="GOODS_NAME">책이름</option>
								<option value="GOODS_PRICE">가격</option>
							</select> <input type="text" name="keyword" value="" id="search"
								placeholder="검색어를 입력하세요.">
							<button class="button color_sub" onclick="fn_search();">검색
							</button>
						</div>
						<!-- 해당 페이지 기능별로 탭 나눠쓰시면됩니다.
                   header footer가 content 기준으로 사이즈가 잡혀있기때문에 구현할 기능들은 content내에서 구현해주세요-->
						<div class="fixedTable">
							<div class="fixedBox" style="overflow-x: hidden; height: 500px">
								<table>
									<thead id="tableHead">
										<tr>
											<th width='20px'>Check</th>
											<th width='30px'>NO</th>
											<th width='30px'>ID</th>
											<th width='50px'>책이름</th>
											<th width='70px'>가격</th>
											<th width='70px'>할인가</th>
											<th width='130px'>할인기간</th>
										</tr>
									</thead>
									<tbody class="table" id="tableBody">
										<c:forEach var="item" items="${bookList}" varStatus="status">
											<tr>
												<!-- 도서목록 -->
												<th width='20px'><input type="checkbox" name="checked"
													id="checked" value="${item.goodsId}"></th>
												<th width='30px'>${status.count}</th>
												<th width='30px'>${item.goodsId}</th>
												<th width='50px'>${item.goodsName}</th>
												<th width='70px' id="checkbtn">${item.goodsPrice}</th>
												<th width='70px' id="Discount"><c:out value="" /></th>
												<th width='60px' id="rateStrDay">할인시작</th>
												<th width='60px' id="rateEndDay">할인끝</th>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div id="button"></div>
							</div>
						</div>
					</div>
				</form>
			</div>
			<!-- tab2!! -->
         <div class="" id="tabs2">
            <form method="post" onSubmit="return false;">
				<div class="content">
				<button class="button color_sub" onclick="lental();">대여 </button>
					<div class="fixedTable">
						<div class="fixedBox" style="overflow-x: hidden; height: 500px">
							<table>
								<thead id="tableHead">
									<tr>
										<th width='20px'>Check</th>	
										<th width='30px'>NO</th>
										<th width='30px'>ID</th>
										<th width='50px'>책이름</th>
										<th width='70px'>가격</th>
										<th width='70px'>할인가</th>
										<th width='130px'>할인기간</th>
									</tr>
								</thead>
								<tbody class="table" id="tableBody">
									<c:forEach var="item" items="${bookList}" varStatus="status">
										<tr>
											<!-- 도서목록 -->
											<th width='20px'>
												<input type="checkbox" name="checked" id="checked" value="${item.goodsId}">
											</th>
											<th width='30px'>${status.count}</th>
											<th width='30px'>${item.goodsId}</th>
											<th width='50px'>${item.goodsName}</th>
											<th width='70px' id="checkbtn">${item.goodsPrice}</th>
											<th width='70px' id="Discount"><c:out value="" /></th>
											<th width='60px' id="rateStrDay">할인시작</th>
											<th width='60px' id="rateEndDay">할인끝</th>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div id="button"></div>
						</div>
					</div>
				</div>
			</form>
         </div>
  <!--        tabs3
         <div class="" id="tabs3">
            <form method="post" onSubmit="return false;">
               <div class="content">
                  테스트3
               </div>
            </form>
         </div>
         tab4
         <div class="" id="tabs4">
            <form method="post" onSubmit="return false;">
               <div class="content">
                  테스트4
               </div>
            </form>
         </div>
      </div>
   </div> -->
   
   <%@include file = "/WEB-INF/jsp/include/footer.jsp" %>

   <!-- popup  -->
   <div id="popupBox" title="테이블"></div>

</body>
</html>