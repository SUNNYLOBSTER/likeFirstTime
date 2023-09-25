<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반문의리스트</title>
</head>
<body>
<%@ include file="./navbar.jsp" %>
	<div id="navContainer">
		${lists}
		<c:choose>
			<c:when test="${lists eq null}">
				<p>진료문의 내역이 없습니다.</p>
			</c:when>
		</c:choose>		
	</div>
</body>
<%@ include file="./footer.jsp" %>
</html>