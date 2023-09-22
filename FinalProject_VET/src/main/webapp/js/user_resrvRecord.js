$(document).on("click",".resrv_cancel",function(){
	console.log("취소버튼 작동");
	var resrv_num = this.parentNode.parentNode.children[0].innerText;
	var resrv_visit = this.parentNode.parentNode.children[2].innerText;
	var resrv_time = this.parentNode.parentNode.children[3].innerText.substr(0,2);
	console.log(resrv_visit, resrv_time);
	alert("예약이 취소됩니다. 지불된 포인트는 반환됩니다.");
	this.parentNode.parentNode.children[4].innerText="취소";
	this.parentNode.style.display="none";
	$.ajax({
		url:"./resrv_userCancel.do",
		method:"post",
		data:{
			resrv_num:resrv_num,
			resrv_visit:resrv_visit,
			resrv_time:resrv_time
			},
		success:function(result){
			console.log(result);
		},
		error:function(){
			alert("예약 취소 실패");
		}
	});
});