

function cancelPay(val){
	
	console.log("환불시작");
	var pay_amount = $("#pay_amount").text();
	var merchant_uid = val;
	console.log(pay_amount,val);
	
	
	$.ajax({
		url:"./cancelPayment.do",
		type:"post",
		data:{
			merchant_uid:merchant_uid,
			cancel_request_amount:pay_amount,
			reason:"테스트 결제 환불"
		},
		dataType:"json",
		success:function(){
			alert("환불 성공");
		},
		error:function(){
			alert("환불 실패");
		}
	});
}	