$(document).ready(function(){
	document.getElementById("btnUseEmail").style.display="none";
});

function checkEmail(){
	var email = document.getElementById("id").value;
	console.log("checkEmail 함수 : ", email);
	
	if(email != ""){
		$.ajax({
			url:"./duplicationAjax.do",
			type:"post",
			data:"checkEmail="+email,
			async: true,
			success: function(data){
				console.log("Ajax 처리된 성공 결과: ", data);
				if(data == "true"){
					document.getElementById("condition").innerHTML = "사용할 수 없는 이메일입니다.";
					document.getElementById("btnUseEmail").style.display = "none";
				} else {
					document.getElementById("condition").innerHTML = "사용가능한 이메일입니다.";
					document.getElementById("btnUseEmail").style.display = "block";
				}
			},
			error: function(){
				alert("잘못된 요청입니다. 관리자에게 문의하세요.");
			}
		});
	}
}

function useEmail(){
	var email = document.getElementById("users_id").value;
	opener.document.getElementById("users_id").value = email;
	window.close();
}