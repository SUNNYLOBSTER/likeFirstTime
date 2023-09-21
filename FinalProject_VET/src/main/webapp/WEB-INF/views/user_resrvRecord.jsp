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
					<th>예약병원</th>
					<th>방문일자</th>
					<th>예약시간</th>
					<th>예약상태</th>
					<th id="cancel">예약취소</th>
				</tr>
				<c:forEach var="list" items="${hosp_lits}">
					<tr>
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
							<jsp:useBean id="sysDate" class="java.util.Date"/>
							<fmt:parseDate var="resrv_date" value="${list.resrv_visit}" pattern="yyyy-MM-dd"/>
							<fmt:formatDate var="resrv" value="${resrv_date}" pattern="yyyy-MM-dd"/>
							<fmt:parseDate var="date" value="${sysDate}" pattern="yyyy-MM-dd"/>
							<fmt:formatDate var="sys" value="${date}" pattern="yyyy-MM-dd"/>
							<c:if test="${sys>resrv}">
								<button>예약 취소</button>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
	</div>
</body>
<%@ include file="./footer.jsp" %>
</html>