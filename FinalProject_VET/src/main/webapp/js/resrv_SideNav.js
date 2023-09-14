
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
			console.log(data.waitLists);
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
				html += "	</tr>";
				for(var i=0; i<data.waitLists.length; i++){
				html += "	<tr>";
				html += "		<td>"+data.waitLists[i].resrv_num+"</td>";
				html += "		<td>"+data.waitLists[i].resrv_visit+"</td>";
				html += "		<td>"+data.waitLists[i].resrv_time+"</td>";
				html += "		<td>"+data.waitLists[i].resrv_name+"</td>";
					if(data.waitLists[i].resrv_status=="W"){
				html += "		<td>예약대기</td>";
					}else{
				html += "		<td>예약확정</td>";	
					}					
				html += "		<td>"+data.waitLists[i].resrv_regdate+"</td>";
				html += "		<td><button>확정</button></td>";
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

function resrv_calendar(){
	console.log("예약현황 호출");
	var calendar= document.getElementById("calendar");
	var month_cnt= document.getElementById("month_cnt");
	var waitList= document.getElementById("waitList");
	calendar.style.display='block';
	month_cnt.style.display='none';
	waitList.style.display='none';
	
}