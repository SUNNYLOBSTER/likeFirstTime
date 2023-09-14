<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>병원 예약관리 페이지</title>
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.0.js"></script>
<script type="text/javascript" src='./js/index.global.js'></script> <!-- 캘린더를 랜더링하는 js -->
<script type="text/javascript" src='./js/resrv_Calendar.js'></script> <!-- 작성할 js -->
<script type="text/javascript" src='./js/resrv_SideNav.js'></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="./css/calendar.css">
</head>
<body>
	<div class="sidenav">
		<div id="Demo1" class="w3-hide">
			<a onclick="resrv_calendar()">예약현황</a>
			<a onclick="month_count()">월별 예약건수</a>
			<a onclick="resrv_wList()">예약승인 대기명단</a>
		</div>
	</div>

	<div class="container">
		<a href="./main.do">메인화면</a>
		<h1>${loginVo.users_name}</h1>
		<div id="calendar"></div>
		<div id="month_cnt"></div>
		<div id="waitList">
		</div>
	</div>
</body>
</html>