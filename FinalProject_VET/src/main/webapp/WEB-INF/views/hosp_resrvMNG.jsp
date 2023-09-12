<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.0.js"></script>
<script type="text/javascript" src='./js/index.global.js'></script> <!-- 캘린더를 랜더링하는 js -->
<script type="text/javascript" src='./js/resrv_Calendar.js'></script> <!-- 작성할 js -->
</head>
<body>
	<button onclick="resrv_monthCount()">월별 예약건수 조회</button><br>
	<button>해당 월에 등록된 예약자 명단</button><br>
	<button>해당날짜에 등록된 확정예약 건수 조회</button><br>
	<button>해당날짜의 각 시간대에 예약확정된 예약자 조회</button><br>
	<button>예약 상세정보를 조회</button><br>
	<button>일별 예약상태에 따른 조회</button><br>
	<hr>
	<div id='calendar'></div>
</body>
</html>