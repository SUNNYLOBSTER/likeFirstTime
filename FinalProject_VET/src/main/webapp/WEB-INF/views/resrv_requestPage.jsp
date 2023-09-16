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
<script type="text/javascript" src='./js/resrv_time.js'></script> <!-- 작성할 js -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="./css/resrv_reqCalendar.css">
</head>
<body>
${user_vo}
	<div class="container">
		<a href="./main.do">메인화면</a>
		<h1>${sessionScope.resrv_hops} 예약페이지</h1>
		<div id="calendar"></div>
		
		<div id="time_area">
			<h2>예약가능 시간</h2>
			<div id="resrv_availableTime"></div>
		</div>
		
		<div>
			<h2>예약자 정보</h2>
			<form action="#" method="post">
				<input type="hidden" value="${user_vo.users_id}">
				이름 : <input type="text" name="" placeholder="예약이름을 작성해주세요" required><br>
				전화번호 : <input type="text" name="" value="">
			</form>
		</div>
		
	</div>
</body>
</html>