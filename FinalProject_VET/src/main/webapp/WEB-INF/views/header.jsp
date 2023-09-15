<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<style type="text/css">
	header {
		color: #3e2723;
		width: 100%;
		height: 100px; padding : 0px;
		margin: 0px;
		padding: 0px;
	}
	
	body {
		margin: 0px;
	}
	
	#container {
		width: 1000px;
		height: 1300px;
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
	}
	
	.loginArea {
		display: inline;
		float: right;
		margin: 10px;
	}
	
	.sidenav {
		height: 100%;
		width: 200px;
		position: fixed;
		z-index: 1;
		top: 0;
		left: 0;
		background-color: #EFEBE9;
		overflow-x: hidden;
		padding-top: 20px;
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
	}
	
	.dropdown-btn {
		background-color: #BCAAA4;
	}
	
	.sidenav a:hover, .dropdown-btn:hover {
		color: #EFEBE9;
	}
	
	.container {
		width: 1000px;
		margin: 0px auto;
		padding: 0px;
		margin-left: 200px;
	}
	
	.dropdown-container {
		display: none;
		background-color: #D7CCC8;
		padding-left: 8px;
S	}
	
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<img alt="logo" src="./img/logo.png" id="logo" onclick="location.href='./main.do'">
		
		<div class="header">
		<c:choose>
		<c:when test="${empty loginVo}">
			<div class="loginArea">
				<button onclick="location.href='./loginForm.do'">로그인</button>
				<button>회원가입</button>
			</div>
		</c:when>
		<c:otherwise>
			<div class="loginArea">			
				<span style="color: #3e2723; font-weight: bold;">
					${loginVo.users_name}님 환영합니다
				</span>
				<c:if test="${loginVo.users_auth eq 'A' }">
				<input type="button" value="관리자 페이지" onclick="location.href='./adminPage.do'">
				</c:if>
				<c:if test="${loginVo.users_auth eq 'H' }">
				<input type="button" value="병원 마이페이지" onclick="location.href='./resrv_Select.do'">
				</c:if>
				<c:if test="${loginVo.users_auth eq 'U' }">
					<input type="button" value="마이페이지" onclick="location.href='./userMyPage.do'">
					<input type="button" value="진료기록" onclick="location.href='./selectAllChart.do'">
				</c:if>
				<button onclick="location.href='./logout.do'">로그아웃</button>
			</div>
		</c:otherwise>
	</c:choose>
	</div>
	
	<div class="header" id="headerNavBar">
		<ul>
			<li><b><a>동물병원 찾기</a></b></li>				
			<li><b><a href="./questBoard.do">진료문의</a></b></li>				
			<li><b><a href="./notice.do">공지사항</a></b></li>				
			<li><b><a>반려동물 정보/뉴스</a></b></li>				
		</ul>
	</div>
</header>
	
</body>

</html>