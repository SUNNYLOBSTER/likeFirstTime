function selectAuth(){
	var userAuth = document.getElementsByName("userAuth")[0];
	var userAuthIndex = userAuth.selectedIndex;
	var info = document.getElementById("info");
	
	var choiceAuth = userAuth.options[userAuthIndex];
	var choiceAuthValue = choiceAuth.value;
	console.log(choiceAuth.value);
	
	//url 걸어서  ajax태우기
	//userController에서 서비스 태우고
	$.ajax({
			url:"./adminPageAuth.do",
			type:"POST",
			async:true,
			data:{"auth":choiceAuthValue},
			success: function(authList){
				var authList = JSON.parse(authList);
				console.log("받아온 요청 값 : ", authList);
				console.log(authList[0].users_id);
				
				
				
			},
			
			error: function(){
				info.innerHTML = "잘못된 요청입니다.";
			}
			
		});

	}

function selectStatus(){
	var userStatus = document.getElementsByName("userStatus")[0];
	var userStatusIndex = userStatus.selectedIndex;
	
	var choiceStatus = userStatus.options[userStatusIndex];
	console.log(choiceStatus.value);
	console.log(choiceStatus.textContent);
	
}


window.onload = function(){
	$("#searchUserId").click(function(){
		$("#userList").css("display","none");
	})
}
	
function searchUserId(){

	var keyword = document.getElementById("keyword").value;
	var info = document.getElementById("info");
	
	console.log(keyword);
	
	if(keyword.length>=1){
		$.ajax({
			url:"./adminPage.do",
			type:"POST",
			async:true,
			data:{"keyword":keyword},
			success:function(msg){
				console.log("요청된 결과값: ", msg);
				if(msg==""){
					info.innerHTML = "회원이 존재하지 않습니다.";
				} else {
					
					info.innerHTML = "";
					var msg = JSON.parse(msg);
					console.log(msg[0], typeof msg[0]);
					var varHtml = "";
					
					varHtml += "<div id='userList'>      ";
					varHtml += "<table>                  ";
					varHtml += "<thead>                  ";
					varHtml += "<tr>                     ";
					varHtml += "<th>번호</th>            ";
					varHtml += "<th>아이디</th>          ";
					varHtml += "<th>이름</th>            ";
					varHtml += "<th>전화번호</th>        ";
					varHtml += "<th>활동상태</th>        ";
					varHtml += "<th>권한</th>            ";
					varHtml += "<th>사업자등록번호</th>  ";
					varHtml += "<th>가입일</th>          ";
					varHtml += "<th>수정하기</th>        ";
					varHtml += "</tr>                    ";
					varHtml += "</thead>					";
					
					varHtml += "<tbody>";
					for (let i=0 ; i<msg.length ; i++) {                                                              
						varHtml += "<tr>                                                                               ";
						varHtml += "<td>"+(i+1)+"</td>                                                               ";
						varHtml += "<td><a href='./selectUserDetail.do?users_id="+msg[i].users_id+"'>"+msg[i].users_id+"</a></td>";
						varHtml += "<td>"+msg[i].users_name+"</td> ";
						varHtml += "<td>"+msg[i].users_tel+"</td>";
						varHtml += "<td>"+msg[i].users_status+"</td>";
						varHtml += "<td>"+msg[i].users_auth+"</td> ";
						varHtml += "<td>"+msg[i].users_crn+"</td>";
						varHtml += "<td>"+msg[i].users_joindate+"</td>";
						varHtml += "<td><input type='button' value='수정' onclick='modifyUser()'> </td>                ";
						varHtml += "</tr>                                                                              ";
					}
					varHtml += "</tbody>";
				}
				
				$("#info").html(varHtml);
			},
			
			error:function(){
				info.innerHTML = "잘못된 요청입니다.";
			}
		});
		
	} else {
		info.innerHTML = "검색어를 2자 이상 입력해주세요.";
	}
}

