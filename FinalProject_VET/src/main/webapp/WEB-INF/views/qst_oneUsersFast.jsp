<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>빠른문의리스트</title>
</head>
<body>
<%@ include file="./navbar.jsp" %>
	<div id="navContainer">
		${lists}
	<hr>
	</div>
</body>
<%@ include file="./footer.jsp" %>
</html>