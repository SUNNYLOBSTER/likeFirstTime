<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제화면</title>
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script type="text/javascript" src="./js/payment_main.js"></script>
<link rel="stylesheet" href="./css/payment_main.css">
</head>
<%@ include file="./header.jsp" %>
<body>
	<div id="container">
		<div id="mileage_10000" class="mileage">
			<h2 class="mileageFont">마일리지 1만원 충전</h2>
		</div>
		<div id="mileage_5000" class="mileage">
			<h2 class="mileageFont">마일리지 5천원 충전</h2>
		</div>
		
		<span>
			<button onclick="requestPay()">결제하기</button>
		</span>
	</div>
	
</body>
<%@ include file="./footer.jsp" %>
</html>