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
</head>
<body>
	<table>
		<tr>
			<th>월</th>
			<th>확정 건수</th>
		</tr>
		<c:forEach var="yLists" items="${monthYLists}">
			<tr>
				<td>${yLists.MM}</td>
				<td>${yLists.Y_COUNT}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>