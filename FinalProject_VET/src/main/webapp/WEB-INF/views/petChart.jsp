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
<script type="text/javascript" src="./js/medichart.js"></script>
</head>
<body>
<h1>일반사용자의 반려동물별 진료기록 리스트</h1>
<c:choose>
	<c:when test="${empty allPetChart}">
		<p>등록된 진료기록이 없습니다</p>
	</c:when>
	<c:otherwise>
		<c:forEach var="alist" items="${allPetChart}">
			<c:forEach var="plist" items="${alist.medichart_vo}">
				<div style="border-collapse: collapse; border: 1px solid black;">
					<table>
							<tr>
								<th>반려동물명</th>
								<td>${alist.pet_name}</td>
							</tr>
							<tr>
								<th>진료기록명</th>
								<td>${plist.medi_title}</td>
							</tr>
							<tr>
								<th>진료날짜</th>
								<td>${plist.medi_visit}</td>
							</tr>
						</table>
				</div>
			</c:forEach>
		</c:forEach>
		${chart}
	</c:otherwise>
</c:choose><br>
<button onclick="window.history.back()">전체목록으로 돌아가기</button>
</body>
</html>