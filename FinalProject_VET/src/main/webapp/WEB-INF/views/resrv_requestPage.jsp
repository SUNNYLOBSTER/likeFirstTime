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
	<div class="container">
		<a href="./main.do">메인화면</a>
		<h1>${sessionScope.resrv_hops} 예약페이지</h1>
		<div id="cal_area">
			<div id="calendar"></div>
			<div id="time_area">
				<h2>예약가능 시간</h2>
				<div id="resrv_availableTime"></div>
			</div>
		</div>
		<hr>
		<div id="resrv_info">
			<h2>예약자 정보</h2>
			<form action="./resrv_insert.do" method="post">
				<input type="hidden" name="resrv_userid" value="${user_vo.users_id}">
				<input type="hidden" name="resrv_hops" value="${sessionScope.resrv_hops}">
				선택 날짜 : <input type="date" id="select_date" name="resrv_visit"><br>
				선택 시간 : <input type="text" id="select_time" name="resrv_time" value="-- : --" readonly><br>
				이름 : <input type="text" name="resrv_name" placeholder="예약이름을 작성해주세요" value="${user_vo.users_name}" required><br>
				전화번호 : <input type="text" name="resrv_tel" placeholder="ex)01012345678" required><br>
				메모 : <input type="text" name="resrv_memo" value="" placeholder="간략한 메모를 남겨주세요"><br>
				<input type="submit" value="예약 신청">
				<button class="btn btn-default" onclick="history.back(-1)">취소</button>
			</form>
		</div>
		
	</div>
</body>
</html>