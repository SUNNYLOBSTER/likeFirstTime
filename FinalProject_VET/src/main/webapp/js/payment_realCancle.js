

function cancelPay(val){
	
	console.log("환불시작", val);
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
	
//	app.post('/payments/cancel', async (req, res, next) => {
//    try {
//      const { body } = req;
//      const { merchant_uid, reason, cancel_request_amount } = body;
//      Payments.find({ merchant_uid }, async function(err, payment) {
//        const paymentData = payment[0]; // 조회된 결제정보
//        const { imp_uid, amount, cancel_amount } = paymentData;
//        const cancelableAmount = amount - cancel_amount;
//        if (cancelableAmount <= 0) { /
//          return res.status(400).json({ message: "이미 전액환불된 주문입니다." });
//        }
//        const getCancelData = await axios({
//          url: "https://api.iamport.kr/payments/cancel",
//          method: "post",
//          headers: {
//            "Content-Type": "application/json",
//          },
//          data: {
//            reason, 
//            imp_uid, 
//            amount: cancel_request_amount, 
//            checksum: cancelableAmount 
//          }
//        });
//        const { response } = getCancelData.data; 
//      });
//    } catch (error) {
//      res.status(400).send(error);
//    }
//  })
//	
}