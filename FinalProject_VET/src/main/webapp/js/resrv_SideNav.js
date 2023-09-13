
function month_count(){
	console.log("월별 예약건수 호출");
	var calendar= document.getElementById("calendar");
	var month_cnt= document.getElementById("month_cnt");
//	console.log(calendar.innerHTML);
	calendar.style.display='none';
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
	var date = new Date();
	calendar.style.display='none';
	month_cnt.style.display='none';
}

function resrv_calendar(){
	console.log("예약현황 호출");
	var calendar= document.getElementById("calendar");
	var month_cnt= document.getElementById("month_cnt");
	calendar.style.disply='block';
	
}