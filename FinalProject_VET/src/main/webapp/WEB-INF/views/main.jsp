<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
</head>
<body>
${loginVo}
<hr>
<div>
	<c:if test="${loginVo != null }">
		<h3>${loginVo.users_name}님 환영합니다.</h3>
		<input type="button" value="로그아웃" onclick="location.href='./logout.do'">
	</c:if>
	<c:if test="${loginVo.users_auth eq 'A' }">
		<input type="button" value="관리자 페이지" onclick="location.href='./adminPage.do'">
	</c:if>
	<c:if test="${loginVo.users_auth eq 'H' }">
		<input type="button" value="병원 마이페이지" onclick="location.href='./hospitalMyPage.do'">
	</c:if>
	<c:if test="${loginVo.users_auth eq 'U' }">
		<input type="button" value="마이페이지" onclick="location.href='./userMyPage.do'">
		<br><br>
		<a href="./selectAllChart.do">진료기록</a>
	</c:if>
</div>
<hr>
<a href="./resrv_Select.do">진료예약조회</a>
<br>
</body>
</html>