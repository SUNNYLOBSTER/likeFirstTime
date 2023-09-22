<%@ page import="java.util.Date"%>
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
<link rel="stylesheet" href="./css/user_resrvRecord.css">
<body>
	<%@ include file="./navbar.jsp" %>
	<div class="navContainer">
		<h2>예약 내역</h2>
			<table class="resrv_record">
				<tr>
					<th>예약번호</th>
					<th>예약병원</th>
					<th>방문일자</th>
					<th>예약시간</th>
					<th>예약상태</th>
					<th id="cancel">예약취소</th>
				</tr>
				<c:forEach var="list" items="${hosp_lists}">
					<tr>
						<td>${list.resrv_num}</td>
						<td>${list.resrv_hops}</td>
						<td>${list.resrv_visit}</td>
						<td>${list.resrv_time}시</td>
						<c:choose>
							<c:when test="${list.resrv_status eq 'Y'}">
								<td>확정</td>
							</c:when>
							<c:when test="${list.resrv_status eq 'N'}">
								<td>취소</td>
							</c:when>
							<c:otherwise>
								<td>대기중</td>
							</c:otherwise>
						</c:choose>
						<td>
							<fmt:parseDate var="resrv_visit" value="${list.resrv_visit}" pattern="yyyy-MM-dd"/>
							<c:set var="now" value="<%= new Date()%>"/>
							<c:set var="visitTime" value="${resrv_visit.time}"/>
							<c:if test="${(now.time-visitTime)<0}">
								<c:if test="${list.resrv_status eq 'Y' || list.resrv_status eq 'W'}">
									<button class="resrv_cancel">예약 취소</button>
								</c:if>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
	</div>
	<script type="text/javascript" src="./js/user_resrvRecord.js"></script>
</body>
<%@ include file="./footer.jsp" %>
</html>