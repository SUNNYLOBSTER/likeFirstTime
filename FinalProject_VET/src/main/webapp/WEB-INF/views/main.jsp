<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
</head>
<%@ include file="./header.jsp" %>
<body>
<div id="container">
	<c:if test="${loginVo.users_auth eq 'U' }">
		<a href="./resrv_requestPage.do?resrv_hops=gana@naver.com">예약신청 페이지</a><br>
	</c:if>
</div>

</body>

<%@ include file="./footer.jsp" %>
</html>