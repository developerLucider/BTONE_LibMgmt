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

<!-- <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> -->
<script src="<c:url value='/js/jquery-1.12.4.min.js'/>"></script>
<script src="<c:url value='/js/jquery.form.js'/>"></script>
<script src="<c:url value='/js/jquery-ui.js'/>"></script>

<script>
	$(() => {
		$("#tabs").tabs();
		/* new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
	            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
	        }
	    }).open(); */
	});
	
	function AllBookListEvent() {
		$("#tableBody tr").each(function () {
			$(this).show();												
		});
	}
	
	function AvailableBookEvent(){
		$("#tableBody tr").each(function () {
			if($(this).children().eq(8).text() != 0) {	// 숨겨진 대여자의 아이디가 존재하면																
				$(this).hide();												
			}
		});
	}
</script>	
	
<script>
	function searchBook() {    

	      var frmData = {
	         "searchType" : $("#searchType option:selected").val(),
	         "keyword" : $("input[name=keyword]").val()
	      }

	      if (frmData["searchType"] && frmData["keyword"]) { //검색 조건이 모두 입력된 경우 

	         $.ajax({
                  url : "main/getBooks.do",
                  type : "POST",
                  contentType : "application/json",
                  data : JSON.stringify(frmData),
                  success : function(data) {
                     if (data.length > 0) {
                        var result = "";
                        $("#tableBody").empty();
						
                        for (i = 0; i < data.length; i++) {
                        	
                       	  	result += "<tr><th width='20px'><input type='checkbox' name='check' value='" + data[i].goodsId + "'></th>"
                            result += "<th width='50px'>" + (i + 1)  + "</th>"
                            result += "<th width='50px'>" + data[i].goodsId + "</th>"
                            result += "<th width='200px'>" + data[i].goodsName + "</th>"
                            result += "<th width='200px'>" + data[i].goodsPrice + "</th>"
                            result += "<th width='100px'>" + data[i].goodsDiscountPrice + "</th>"
                            if(data[i].eventStrDate) {	
                                result += "<th width='100px'>" + data[i].eventStrDate + "</th>"
                            } else {	// null일경우는 비워두도록
                            	result += "<th width='100px'></th>"
                            }
                            if(data[i].eventEndDate) {
                                result += "<th width='100px' id='eventEndDate" + i + "'>" + data[i].eventEndDate + "</th>"
                            } else {	// null일경우는 비워두도록
                            	result += "<th width='100px' id='eventEndDate" + i + "'></th>"
                            }
                            if(data[i].userId) {
                            	result += "<th width='100px' id='userId" + i + "' style='display:none'>" + data[i].userId + "</th></tr>"	
                            } else {
                            	result += "<th width='100px' id='userId" + i + "' style='display:none'></th>"
                            } 
                        }

                        $("#tableBody").append(result);
                     } else {
                        alert("검색 결과가 없습니다.");
                     }
                     
                     $("#allList").trigger("click");
                  }
              });
	      } else if (!frmData["keyword"]) { //키워드가 없을경우 
	         location.reload(); // 전체 조회로 다시
	      } else {
	         alert("검색조건을 확인해주세요.");
	      }
	   }
</script>

<script>
function rentBook() {    
	var rentBookList = [];
	
	$("#tableBody tr").each(function () {
		if($(this).find('input:checkbox[name=check]').is(":checked")) {
			rentBookList.push($(this).children().eq(2).text());
		}
	});
	
	console.log(rentBookList.length);
	
	// 체크된 값이 없을때
	if (rentBookList.length > 0) {
		$.ajax({
			url : "main/rentBooks.do",
			type : "post",
			data : {
				"rentBookList" : rentBookList,
				"userNo" : 2
			},
			success : function(data) {
				if(!data) {
					alert("해당 책은 이미 빌려갔습니다.");
				}
				alert("대여 완료");
				location.reload();
			},
			error : function(xhr, status, error) {
				alert("에러발생");
			}
		});
	} else {
		alert("대여 할 책을 고른뒤 대여 버튼을 눌러주세요.");		
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
								<li><a id="allList" href="#tabs1" onclick="AllBookListEvent();">전체조회</a></li>
								<li><a id="rentList" href="#tabs1" onclick="AvailableBookEvent();">대여가능</a></li>
							</ul>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- tabs1 -->
			<div class="content" id="tabs1">
				<div class="search_select">
                     <select id="searchType" name="searchType">
                        <option value="">--선택--</option>
                        <option value="GOODS_ID">아이디</option>
                        <option value="GOODS_NAME">이름</option>
                        <option value="GOODS_PRICE">가격</option>
                     </select> <input type="text" name="keyword" value="" id="search"
                        placeholder="검색어를 입력하세요.">
                     <button class="button color_sub" onclick="searchBook();">검색</button>
                     <button class="button color_sub4" onclick="rentBook();">대여</button>
           		</div>
				<div class="fixedTable">
					<div class="fixedBox">
						<table>
							<thead id="tableHead">
								<tr>
									<th width='20px'></th>
									<th width='50px'>No</th>
									<th width='50px'>ID</th>
									<th width='200px'>이름</th>
									<th width='200px'>가격</th>
									<th width='100px'>이벤트가</th>
									<th width='100px'>이벤트시작기간</th>
									<th width='100px'>이벤트종료기간</th>
								</tr>
							</thead>
							<tbody class="table" id="tableBody">
								<c:forEach var="list" items="${list}" varStatus="status">
									<tr>
										<th width='20px'><input type="checkbox" name="check"
											value="${list.goodsId}"></th>
										<th width='50px'>${status.count}</th>
										<th width='50px'>${list.goodsId}</th>
										<th width='200px'>${list.goodsName}</th>
										<th width='200px'>${list.goodsPrice}</th>
										<th width='100px'>${list.goodsDiscountPrice}</th>
										<th width='100px'>${list.eventStrDate}</th>
										<th width='100px'>${list.eventEndDate}</th>
										<th width='100px' style="display:none">${list.userId}</th>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>

				</div>
			</div>
			<!-- tab2 -->

		</div>
	</div>
	
	<%@include file = "/WEB-INF/jsp/include/footer.jsp" %>

	<!-- popup  -->
	<div id="popupBox" title="테이블"></div>

</body>
</html>