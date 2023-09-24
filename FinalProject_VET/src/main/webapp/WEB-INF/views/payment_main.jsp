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
		<div id="payment_info">
			<p>
				포인트를 구매하여 다양한 기능을 이용해보세요!<br>
				<b>포인트 사용법</b><br>
				첫번째, 진료 문의글 작성 시 빠른문의글을 등록할 수 있어요.<br>
				전문가의 답변을 보다 빠르게 받아보세요.<br>
				두번째, 동물병원 진료 예약시 예약금을 포인트로 결제할 수 있어요.  
			</p>
		</div>
		<input type="hidden" value="${buyer_info.buyer_tel}" id="buyer_tel">
		<input type="hidden" value="${buyer_info.buyer_addr}" id="buyer_addr">
		<input type="hidden" value="${buyer_info.buyer_name}" id="buyer_name">
		<input type="hidden" value="${buyer_info.buyer_email}" id="buyer_email">
		<button id="mileage_10000" class="mileage" onclick="requestPay(this.value)" value="10000">
			<h2 class="mileageFont">포인트 1만원 충전</h2>
		</button>
		<button id="mileage_5000" class="mileage" onclick="requestPay(this.value)" value="5000">
			<h2 class="mileageFont">포인트 5천원 충전</h2>
		</button>
		
	</div>
	
</body>
<%@ include file="./footer.jsp" %>
</html>