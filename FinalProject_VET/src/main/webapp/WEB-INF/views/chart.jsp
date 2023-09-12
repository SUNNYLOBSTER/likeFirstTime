<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>allChart</title>
</head>
<body>
<h1>일반사용자별 전체 진료기록 리스트</h1>
	<c:forEach var="pet" items="${allPets}" varStatus="vs">
		${vs.count} : <button onclick="location.href='selectPetChart.do?pet_name=${pet.pet_name}'"><c:out value="${pet.pet_name}"/></button><br>
	</c:forEach>
	${allPets}
	${allCharts}
</body>

</html>

