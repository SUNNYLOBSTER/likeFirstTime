

function cancelPay(val){
	
	console.log("환불시작");
	var pay_amount = $("#pay_amount").text();
	var imp_uid = val;
	console.log(pay_amount,val);
	
	$.ajax({
		url:"./cancelPayment.do",
		type:"post",
		data:{
			cancel_request_amount:pay_amount,
			reason:"테스트 결제 환불",
			imp_uid:imp_uid
		},
		dataType:"json",
		success:function(data){
				console.log("취소완료");
				alert("결제가 취소되었습니다.");
				location.href="./selectAllPayment.do";
		},
		error:function(){
			location.href="./selectAllPayment.do";
		}
	});
}	