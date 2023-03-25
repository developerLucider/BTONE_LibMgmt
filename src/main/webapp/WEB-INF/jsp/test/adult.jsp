<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
         
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>인증 페이지</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/common.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jquery-ui.css'/>" />

<script src="<c:url value='/js/jquery-1.12.4.min.js'/>"></script>
<script src="<c:url value='/js/jquery.form.js'/>"></script>
<script src="<c:url value='/js/jquery-ui.js'/>"></script>
 <style>
      label {
        display: inline-block;
        width: 100px;
        text-align: right;
        margin-right: 10px;
      }
      input {
        width: 200px;
      }
    </style>
    
    
<script>
//주민번호 최대자리 입력시 다음 칸으로 바로 이동하는 뻥션
$(document).ready(function() {
    $(".inputs").keyup(function () {
        if (this.value.length == this.maxLength) {
          $(this).next('.inputs').focus();
        }
    });
});

function adult(){
	
	$.ajax({
		url : "/main/adult.do",
		dataType : "json",
        type :   "post",
        contentType : "application/json",
		data :{
			userName : $("#userName").val(),
			userRegNo : $("#userRegNo").val() + $("#userRegNo2").val()
		},
		
	    success : function(result) {  //아래와 같은 데이터 형식으로 가져올거임!
	    	console.log( "주민번호 조회 성공");
	    	alert(JSON.stringify('인증 완료!'));
	    	location.reload();    	
	    	
	    },error : function(e){
	    	console.log(e);
	    	alert(e);
	    }
		      
	}); 
}



</script>

</head>
<body>
   <%@include file = "/WEB-INF/jsp/include/header.jsp" %>
   <div class="container">

         <div class="" id="tabs1">
               <div class="content">                   
                  <div class="/adult/action">
                     <h3>성인 인증</h3>   
                         
                        <label for="userName">이름:</label>
                         <input type="text" id="userName" name="userName"><br>
                     
                         <label for="userRegNo">주민등록번호:</label>
                         <input class="inputs" type="text" id="userRegNo" name="userRegNo" maxlength="6" placeholder="주민번호 앞자리"> -  
                         <input class="inputs" type="password" id="userRegNo2" name="userRegNo" maxlength="7" placeholder="주민번호 뒷자리"> 
                         
                         <!-- <input class="inputs" type="text" id="userRegNo" name="userRegNo" maxlength="7"> -->
                        <button onclick="adult();">인증</button>
                       	 ${text}
                  </div>   
                  
               </div> <!-- content -->
         </div> <!-- tab1 -->
   </div> <!-- contabiner -->
   <%@include file = "/WEB-INF/jsp/include/footer.jsp" %>
</body>
</html>