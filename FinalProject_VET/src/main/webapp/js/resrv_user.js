
function pageFirst(){
	console.log("pageFirst 처음페이지로 이동");
	spa_ajax(1);
}

function pagePrev(stagePage, countPage){
	console.log("pagePrev 이전 페이지 묶음으로 이동");
	var page = (stagePage - countPage) < 0 ? 1 : (stagePage-countPage);
	spa_ajax(page);
}

function page(page){
	console.log("page 현재 선택된 페이지로 이동");
	spa_ajax(page);
}

function pageNext(stagePage, countPage){
	console.log("pageNext 다음 페이지 묶음으로 이동");
	spa_ajax(stagePage+countPage);
}

function pageLast(totalPage){
	console.log("pageLast 끝 페이지로 이동");
	spa_ajax(totalPage);
}

//TODO 19_04 SPA로 처리할 jQuery Ajax 익명함수



var spa_ajax = function(...args){
	var choice_page = args[0];
	console.log("choice_page : ",choice_page);
	
//	$.ajax({
//		url:"./resrv_recordList.do",
//		type:"post",
//		async:true,
//		data: {"page": choice_page},
//		dataType:"json",
//		success: function(data){
//			console.log(data);
//		},
//		error: function(e){
//			console.log("잘못된 요청처리 ",e);
//		}
//	});
}
//-----------------------------------------

function resrv(id){
	console.log(id);
	$.ajax({
		url:"./resrv_recordList.do",
		type:"post",
		data:"resrv_userid="+id,
		dataType:"json",
		success:function(result){
			console.log(result[0].resrv_visit.substr(0,10));
			var resrv_record = document.getElementById("resrv_record");
			
			if(result.length == 0){
				resrv_record.innerHTML="<p>예약신청 기록이 없습니다.</p>"
			}else{
				var html = "";
				for(let i=0; i<result.length; i++){
					html +="<table class='resrv_detail'>                          ";
					html +="<tr>                             ";
					html +="	<th>예약번호</th>            ";
					html +="	<td>"+result[i].resrv_num+"</td>                    ";
					html +="	<th>동물병원명</th>          ";
					html +="	<td>"+result[i].resrv_hops+"</td>                    ";
					html +="</tr>                            ";
					html +="<tr>                             ";
					html +="	<th>예약날짜</th>            ";
					html +="	<td>"+result[i].resrv_visit.substr(0,10)+"</td>                    ";
					html +="	<th>예약시간</th>            ";
					html +="	<td>"+result[i].resrv_time+"시</td>                    ";
					html +="</tr>                            ";
					html +="<tr>                             ";
					html +="	<th>예약자명</th>            ";
					html +="	<td>"+result[i].resrv_name+"</td>                    ";
					html +="	<th>예약상태</th>            ";
					if(result[i].resrv_status == "Y"){
						html +="	<td>확정</td>                    ";
					}else if(result[i].resrv_status == "N"){
						html +="	<td>대기</td>                    ";
					}else{
						html +="	<td>취소</td>                    ";
					}
					html +="</tr>                            ";
					html +="<tr>                             ";
					html +="	<th colspan='4'>메모</th>    ";
					html +="</tr>                            ";
					html +="<tr>                             ";
					html +="	<td colspan='4'>"+result[i].resrv_memo+"</td>        ";
					html +="</tr>                            ";
				    html +="</table>";
				}
				resrv_record.innerHTML = html;
			}
		},
		error:function(){
			alert("호출 에러");
		}
	});
}

