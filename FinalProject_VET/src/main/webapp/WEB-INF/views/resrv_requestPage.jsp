<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약 신청 페이지 (일반사용자)</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.0.js"></script>
<script type="text/javascript" src='./js/index.global.js'></script> <!-- 캘린더를 랜더링하는 js -->
<script type="text/javascript" src='./js/resrv_reqCal.js'></script> <!-- 작성할 js -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="./css/calendar.css">
</head>
<body>
	${user_vo.users_id}
	<a href="./main.do">메인화면</a>
	<div class="container">
		<h1>${resrv_hops} 예약페이지</h1>
		<div id="calendar"></div>
	</div>
</body>
</html>