//function cancelPay(val){
//	console.log("환불실행");
//	var merchant_uid = val;
//	console.log(merchant_uid);
//	var pay_amount = $(".selectBtn").siblings().eq(1).text();
//	var pay_amount = $(".selectBtn").parent().children().first().next().text();
//	var pay_amount = $(".selectBtn").parent().children().eq(1).text();
//	var pay_amount = $(".selectBtn").parent().children("#pay_amount").text();
//	
//	console.log(pay_amount);
//	
//	$.ajax({
//		url:"./canclePay.do",
//		method:"post",
//		contentType:"application/json",
//		data:{
//			merchant_uid : merchant_uid,
//			cancel_request_amount : 
//		}
//		
//	});
//	
//	
//}

$(document).on("click"),function cancelPay(){
	console.log("환불실행");
}