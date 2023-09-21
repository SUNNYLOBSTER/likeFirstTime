<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제내역</title>
<script type="text/javascript" src="./js/payment_main.js"></script>
<link rel="stylesheet" href="./css/payment_list.css">
</head>
<body>
<%@ include file="./navbar.jsp" %>
	<div class="navContainer">
		<h2>결제내역</h2>
		<table id="chartInfo">
			<tr>
				<th>결제번호</th>
				<th>결제금액</th>
				<th>결제시간</th>
				<th>결제방식</th>
				<th>결제상태</th>
				<th>환불</th>
				<th></th>
			</tr>
			<c:forEach var="list" items="${lists}">
				<fmt:parseDate var="payDate" value="${list.pay_time}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:parseDate>
				<c:set var="now" value="<%= new Date() %>"></c:set>
				<c:set value="${payDate.time}" var="pay_date"></c:set>
				<tr>
					<td>${list.pay_num}</td>
					<td id="pay_amount">${list.pay_amount}</td>
					<td>${list.pay_time}</td>
					<td>${list.pay_method}</td>
					<c:choose>
						<c:when test="${list.pay_status eq 'Y'}">
							 <td>결제완료</td>
							 <c:choose>
							 	<c:when test="${(now.time - pay_date) / (1000 * 60 * 60 * 24) <= 3}">
							 		<td><button class="selecteBtn">신청</button></td>
							 	</c:when>
							 </c:choose>
						</c:when>
						<c:when test="${list.pay_status eq 'C'}">
							<td>환불완료</td>
						</c:when>
					</c:choose>
				</tr>
			</c:forEach>
		</table>
		<span>
			<button id="paymentBtn" onclick="location.href='./goPayment.do'">결제하기</button>
		</span>
	</div>
</body>
<script type="text/javascript" src="./js/payment_cancel.js"></script>
<%@ include file="./footer.jsp" %>
</html>