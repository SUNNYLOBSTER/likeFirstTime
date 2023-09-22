$(document).on("click",".resrv_cancel",function(){
	console.log("취소버튼 작동");
	var resrv_num = this.parentNode.parentNode.children[0].innerText;
	console.log(resrv_num);
	alert("예약이 취소됩니다. 지불된 포인트는 반환됩니다.");
	this.parentNode.parentNode.children[4].innerText="취소";
	this.parentNode.style.display="none";
	$.ajax({
		url:"./resrv_userCancel.do",
		method:"post",
		data:"resrv_num="+resrv_num,
		success:function(result){
			console.log(result);
		},
		error:function(){
			alert("예약 취소 실패");
		}
	});
});