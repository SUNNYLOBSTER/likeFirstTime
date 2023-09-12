<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>allPetChart</title>
</head>
<body>
<h1>일반사용자의 반려동물별 진료기록 리스트</h1>
<c:choose>
	<c:when test="${empty allPetChart}">
		<p>등록된 진료기록이 없습니다</p>
	</c:when>
	<c:otherwise>
		<c:set var="chart" value="${allPetChart}"/>
		${chart}
	</c:otherwise>
</c:choose><br>
<button onclick="window.history.back()">전체목록으로 돌아가기</button>
</body>
</html>