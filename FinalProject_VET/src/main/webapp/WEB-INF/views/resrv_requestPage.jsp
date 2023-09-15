<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약 신청 페이지 (일반사용자)</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.0.js"></script>
<script type="text/javascript" src='./js/index.global.js'></script> <!-- 캘린더를 랜더링하는 js -->
<script type="text/javascript" src='./js/resrv_reqCal.js'></script> <!-- 작성할 js -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="./css/resrv_reqCalendar.css">
</head>
<body>
	${user_vo.users_id}
	<div class="container">
		<a href="./main.do">메인화면</a>
		<h1>${sessionScope.resrv_hops} 예약페이지</h1>
		<div id="calendar"></div>
		<h2>예약가능 시간</h2>
		<div id="resrv_availableTime">
			<c:forEach var="i" begin="0" end="24">
				
			</c:forEach>
			${hosp_time}
			
		</div>
		
	</div>
</body>
</html>