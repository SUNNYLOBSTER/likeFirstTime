<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>포인트내역</title>
<link rel="stylesheet" href="./css/payment_list.css">
</head>
<body>
<%@ include file="./navbar.jsp" %>
	<div class="navContainer">
		<h3 id="listname">포인트내역</h3>
		<div id="wholePnt">
			<p id="point_list">전체 가용 포인트 : ${point}</p>
		</div>
		<table id="chartInfo">
			<tr>
				<th>적립/사용일자</th>
				<th>포인트</th>
				<th>이용내역</th>
			</tr>
			<c:forEach var="list" items="${lists}">
				<tr>
					<td>${list.pnt_date}</td>
					<c:choose>
						 <c:when test="${list.pnt_point ne null && list.pnt_point < 0}">
							<td>${list.pnt_point}&nbsp;차감</td>
							<c:choose>
								<c:when test="${list.pnt_point == -3000}">
									<td>예약금 차감</td>
								</c:when>
								<c:when test="${list.pnt_point == -500}">
									<td>우선답변글 등록</td>
								</c:when>
								<c:when test="${list.pnt_point == -10000}">
									<td>결제취소</td>
								</c:when>
								<c:when test="${list.pnt_point == -5000}">
									<td>결제취소</td>
								</c:when>
							</c:choose>
						</c:when>
						<c:when test="${list.pnt_point ne null && list.pnt_point > 0}">
							<td>${list.pnt_point}&nbsp;충전</td>
							<td>충전</td>
						</c:when>
					</c:choose>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
<%@ include file="./footer.jsp" %>
</html>