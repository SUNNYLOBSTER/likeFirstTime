
function month_count(){
	console.log("월별 예약건수 호출");
	var calendar= document.getElementById("calendar");
	var month_cnt= document.getElementById("month_cnt");
	var waitList= document.getElementById("waitList");
//	console.log(calendar.innerHTML);
	calendar.style.display='none';
	waitList.style.display='none';
	month_cnt.style.display='block';
	var date = new Date();
	var year = date.getFullYear();
	
	$.ajax({
		url:"./resrv_monthCount.do",
		method:"POST",
		dataType:"json",
		data:{yyyy:year},
		success:function(data){
			console.log(data.lists);
			var html = "";
				html += "<h2>"+year+"년도</h2>";
				html += "<table>";
				html += "	<tr>";
				html += "		<th>월</th>";
				html += "		<th>예약건수</th>";
				html += "	</tr>";
				for(var i=0; i<data.lists.length; i++){
				html += "	<tr>";
				html += "		<th>"+data.lists[i].MM+"</th>";
				html += "		<th>"+data.lists[i].Y_COUNT+"</th>";
				html += "	</tr>";
				}
				html += "</table>";
			month_cnt.innerHTML=html;
		},
		error:function(){
			alert("호출 에러");
		}
	});
};

function resrv_wList(){
	console.log("예약승인 대기명단 호출");
	var calendar= document.getElementById("calendar");
	var month_cnt= document.getElementById("month_cnt");
	var waitList= document.getElementById("waitList");
	calendar.style.display='none';
	month_cnt.style.display='none';
	waitList.style.display='block';
	$.ajax({
		url:"./resrv_waitLists.do",
		method:"post",
		dataType:"json",
		data:{resrv_status:"W"},
		success:function(data){
			console.log(data[0]);
			var html = "";
				html += "<table>";
				html += "	<tr>";
				html += "		<th>예약번호</th>";
				html += "		<th>예약날짜</th>";
				html += "		<th>예약시간</th>";
				html += "		<th>예약이름</th>";
				html += "		<th>예약상태</th>";
				html += "		<th>접수시간</th>";
				html += "		<th>예약승인</th>";
				html += "		<th>예약거절</th>";
				html += "	</tr>";
				for(var i=0; i<data.length; i++){
				html += "	<tr>";
				html += "		<td>"+data[i].resrv_num+"</td>";
				html += "		<td>"+data[i].resrv_visit+"</td>";
				html += "		<td>"+data[i].resrv_time+"</td>";
				html += "		<td>"+data[i].resrv_name+"</td>";
					if(data[i].resrv_status=="W"){
				html += "		<td>예약대기</td>";
					}else{
				html += "		<td>예약확정</td>";	
					}					
				html += "		<td>"+data[i].resrv_regdate+"</td>";
				html += "		<td><button class='resrv_confirm'>확정</button></td>";
				html += "		<td><button class='resrv_refuse'>거절</button></td>";
				html += "	</tr>";
				}
				html += "</table>";
			waitList.innerHTML=html;
		},
		error:function(){
			alert("호출 에러");
		}
	});
}

$(document).on("click",".resrv_confirm",function(){
	console.log("resrv_confirm 확정버튼 작동");
	resrv_num = this.parentNode.parentNode.children[0].innerText;
	resrv_refuseBtn = this.parentNode.parentNode.children[7].children[0];
	resrv_btn = this;
	console.log(resrv_refuseBtn);
//	console.log(resrv_num);
	$.ajax({
		url:"./resrv_confirm.do",
		method:"post",
		data:"resrv_num="+resrv_num,
		success:function(result){
			console.log(result);
			if(result == "confirm"){
				resrv_btn.style.display="none";
				resrv_refuseBtn.style.display="none";
				resrv_btn.parentNode.innerHTML="완료";
			}else{
				
			}
		},
		error:function(){
			
		}
	});
});
$(document).on("click",".resrv_refuse",function(){
	console.log("resrv_refuse 거절버튼 작동");
	resrv_num = this.parentNode.parentNode.children[0].innerText;
	resrv_confirmBtn = this.parentNode.parentNode.children[6].children[0];
	resrv_btn = this;
	$.ajax({
		url:"./resrv_refuse.do",
		method:"post",
		data:"resrv_num="+resrv_num,
		success:function(result){
			console.log(result);
			if(result == "resrv_refuse"){
				resrv_btn.style.display="none";
				resrv_confirmBtn.style.display="none";
				resrv_btn.parentNode.innerHTML="거절";
			}else{
				
			}
		},
		error:function(){
			
		}
	});
});


function resrv_calendar(){
	console.log("예약현황 호출");
	var calendar= document.getElementById("calendar");
	var month_cnt= document.getElementById("month_cnt");
	var waitList= document.getElementById("waitList");
	calendar.style.display='block';
	month_cnt.style.display='none';
	waitList.style.display='none';
	
}