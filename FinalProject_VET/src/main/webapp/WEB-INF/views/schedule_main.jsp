<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일정관리 게시판</title>
<script type="text/javascript" src='./js/index.global.js'></script> 
<script type="text/javascript" src="./js/schedule_calendar.js"></script>
<link rel="stylesheet" href="./css/calendar.css">
</head>
<%@ include file="./navbar.jsp" %>
<body>
	<div class="navContainer">
		<div id="calendar"></div>
	</div>
</body>
<%@ include file="./footer.jsp" %>
</html>