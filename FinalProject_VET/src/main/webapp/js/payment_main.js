
	var merchant_uid = merchant_uid + 1;
	var point = 10000;
	var IMP = window.IMP;
	IMP.init("imp40440345");
	
	﻿function requestPay() {
        IMP.request_pay(
            {
            	pg: "kcp", //결제사 선택 포트원 사이트 참고
                pay_method: "card", // 카드결제 선택
                merchant_uid: merchant_uid,
                name: "마일리지"+point+"원 충전",
                amount: point,
                buyer_email: "mileagetest@testemail.com",
                buyer_name: "테스트 구매자 김지인",
                buyer_tel: "010-6703-3555",
                buyer_addr: "서울특별시 금천구 가산동",
                buyer_postcode: "123-456",
//                 pay_code: "inicis", 
//                 pay_amount: 10000,
//                 pay_id: "elsa@disney.com"
            },
            function (rsp) {
            	console.log(rsp);
            	
            	if(rsp.success){
            		var msg = '결제가 완료되었습니다';
//             		msg += "결제 ID : " + rsp.pay_id;
//             		msg += "결제 금액 : " + rsp.pay_amount;
            		alert(msg);
            		
            		$.ajax({
            			url:"./insertNewPayment.do",
            			method:"post",
            			data
            			
            			
            		});
            		
            	}else{
            		var msg = '결제에 실패하였습니다';
            		msg += '에러내용 : ' + rsp.error_msg;
            		console.log('결제실패');
            		alert(msg);
            	}
		});
	}
