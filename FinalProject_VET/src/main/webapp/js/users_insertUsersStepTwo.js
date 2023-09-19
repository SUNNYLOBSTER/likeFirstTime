function emailCert() {

$('#emailCheckBtn').click(function(){
	var email = $('#users_id').val() //email 주소값 얻어오기
	console.log("이메일주소: ", email);
	
	var checkInput = $('.emailCheckInput')//인증번호 입력하는 자리
	
	$.ajax({
		type:"get",
		url:"<c:url value='/mailCheck?email='/>"+email,
		success: function(data){
			console.log("data : ", data);
			checkInput.attr('disabled', false);
			code = data;
			alert("인증번호가 전송되었습니다.");
		}
	});
});

}