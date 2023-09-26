<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style type="text/css">
	header {
		color: #3e2723;
		width: 100%;
		height: 100px; padding : 0px;
		margin: 0px;
		padding: 0px;
		z-index: 50;
	}
	
	body {
		margin: 0px;
	}
	
	#container {
		width: 1000px;
		height: 900px;
		margin: 0px auto;
   		padding: 0px;

	}
	
	footer {
		background-color: #efebe9;
		height: 100px;
		width: 100%;
		text-align: center;
		padding: 20px;
	}
	
	a {
		text-decoration: none;
		color: inherit;
	}
	
	ul {
		list-style: none;
		margin: 0px;
		padding: 10px;
	}
	
	li {
		display: inline;
		float: right;
		font-size: 20px;
		margin-right: 20px;
	}
	
	.header {
		width: 100%;
		height: 50px;
		background: #efebe9;
	}
	
	#logo {
		width: 90px;
		height: 90px;
		margin: 5px;
		position: absolute;
		z-index: 99;
	}
	
	.loginArea {
		display: inline;
		float: right;
		margin: 10px;
	}
	
	.sidenav {
		height: 900px;
		width: 200px;
		position: absolute;
		z-index: 1;
		top: 0;
		left: 0;
		background-color: #D7CCC8;
		overflow-x: hidden;
		margin-top: 100px;
	}
	
	.sidenav a, .dropdown-btn {
		padding: 6px 8px 6px 16px;
		text-decoration: none;
		font-size: 20px;
		color: #3E2723;
		display: block;
		border: none;
		background: none;
		width: 100%;
		text-align: left;
		cursor: pointer;
		outline: none;
		line-height: inherit;
	}
	
	.dropdown-btn {
		background-color: #BCAAA4;
	}
	
	.sidenav a:hover, .dropdown-btn:hover {
		color: #EFEBE9;
	}
	
	.dropdown-container {
		display: none;
		background-color: #D7CCC8;
		padding-left: 8px;
	}
	
	.navContainer{
		width: 1000px;
		margin: 0px auto;
		height: 900px;
 		padding-left: 220px; 
 		padding-top: 30px;
	}
	
	#sideNav_1{
		margin-top: 50px;
	}
	#loginForm{
		margin: 50px auto;
	}
	.fa-caret-down {
	    float: right;
	    padding-right: 8px;
	}
	
/* 	.container button,input[type="submit"],input[type="button"]{ */
/* 		background-color: #D7CCC8; */
/* 		width: 110px; */
/* 		height: 20px; */
/* 		border-radius: 5px; */
/* 		border: none; */
/* 		box-shadow: 1.5px 1.5px 1.5px 0 #3E2723; */
/* 		margin: 5px; */
/* 	} */
	
/* 	.container button:hover,input[type="submit"]:hover,input[type="button"]:hover{ */
/* 		background-color:  #BCAAA4; */
/* 	} */
	
</style>
</head>
<body>
	<header>
		<img alt="logo" src="./img/logo.png" id="logo" onclick="location.href='./main.do'">
		
		<div class="header">
		<c:choose>
		<c:when test="${empty loginVo}">
			<div class="loginArea">
				<button onclick="location.href='./loginForm.do'">로그인</button>
				<button onclick="location.href='./insertUsers.do'">회원가입</button>
			</div>
		</c:when>
		<c:otherwise>
			<div class="loginArea">			
				<span style="color: #3e2723; font-weight: bold;">
					${loginVo.users_name}님 환영합니다
				</span>
				<c:if test="${loginVo.users_auth eq 'A' }">
					<button onclick="location.href='./adminPage.do'">관리자페이지</button>
				</c:if>
				<c:if test="${loginVo.users_auth eq 'H' }">
					<button onclick="location.href='./resrv_Select.do'">병원 마이페이지</button>
				</c:if>
				<c:if test="${loginVo.users_auth eq 'U' }">
					<button onclick="location.href='./selectAllChart.do'">마이페이지</button>
				</c:if>
				<button onclick="location.href='./logout.do'">로그아웃</button>
				
			</div>
		</c:otherwise>
	</c:choose>
	</div>
	
	<div class="header" id="headerNavBar">
		<ul>
			<c:if test="${loginVo.users_auth ne 'H'}">
				<li><b><a href="./map.do">동물병원 찾기</a></b></li>				
			</c:if>
			<li><b><a href="./questBoard.do">진료문의</a></b></li>				
			<li><b><a href="./notice.do">공지사항</a></b></li>				
			<li><b><a>반려동물 정보/뉴스</a></b></li>				
		</ul>
	</div>
</header>
	
</body>

</html>