<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%@ include file="./header.jsp" %>
<body>
	<div class="sidenav">
		<button id="sideNav_1" class="dropdown-btn">
			진료기록<i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-container">
			<a onclick="#">1</a>
		</div>
		<button class="dropdown-btn">
			진료문의 내역<i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-container">
			<a onclick="#">1</a>
		</div>
		<button class="dropdown-btn">
			일정관리<i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-container">
			<a onclick="#">1</a>
		</div>
		<button class="dropdown-btn">
			예약내역<i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-container">
			<a onclick="#">1</a>
		</div>
		<button class="dropdown-btn">
			결제내역<i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-container">
			<a onclick="#">1</a>
		</div>
		<button class="dropdown-btn">
			즐겨찾기<i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-container">
			<a onclick="#">1</a>
		</div>
		<button class="dropdown-btn">
			내 정보관리<i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-container">
			<a onclick="#">1</a>
		</div>
		<button class="dropdown-btn">
			채팅<i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-container">
			<a onclick="#">1</a>
		</div>
	</div>
	<div class="navContainer">
		<a href="./main.do">메인화면</a>
		<h1>${loginVo.users_name}</h1>
		<div id="calendar"></div>
		<div id="month_cnt"></div>
		<div id="waitList"></div>
	</div>
</body>
<%@ include file="./footer.jsp" %>
</html>