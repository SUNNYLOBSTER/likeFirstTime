<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
</head>
<%@ include file="./header.jsp" %>
<body>
<div id="container">
${noticeLists}
</div>
</body>
<%@ include file="./footer.jsp" %>
</html>