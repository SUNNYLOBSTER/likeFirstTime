/**
 * 진료기록 조회 js
 */
 
function codeLChange(){
		var codeL = document.getElementById("codeL");
		var selectedValue = codeL.options[codeL.selectedIndex].value;
		console.log("선택된 대분류 : ",selectedValue);
		
		$.ajax({
			url:'./listByCodeS.do',
			method:'post',
			data: "medicodeL="+selectedValue,
			success:function(data){
				console.log(data.list[0].medi_name);
				var obj = $("#codeS");
				var html = "";
				for(let i=0; i<data.list.length ;i++ ){
				 	html += "<option value='"+data.list[i].medi_code+"'>"+data.list[i].medi_name+"</option>";
					obj.append(html);
				}
				obj.empty().append(html);
			},
			error:function(error){
				console.log("값 전달 오류");
			}
		});
	}
	
	function selectCode() {
		console.log("selectCode 실행");
		var codeL = document.getElementById("codeL");
		var selectedL = codeL.options[codeL.selectedIndex].value;
		
		var codeS = document.getElementById("codeS");
		var selectedS = codeS.options[codeS.selectedIndex].value;
		
		console.log("선택된 대분류, 소분류 : ", selectedL, selectedS);
		
		$.ajax({
			url:'./selectSChart.do',
			method:'post',
			data:{
				medi_l:selectedL,
				medi_s:selectedS
			},
			success:function(data){
				console.log("전달받은 리스트 : ",data);
				
				var listname = document.getElementById('listname');
				listname.innerHTML = '<p>진료과목별 진료기록</p>';
				if(data.lists.length == 0){
				var chartPart = document.getElementById('chartPart');
				chartPart.innerHTML = '<div>일치하는 진료기록이 없습니다</div>';	
				}else{
					var chartPart = document.getElementById('chartPart');
					chartPart.innerHTML = '';
					var html = "";
					for (var i = 0; i < data.lists[0].medichart_vo.length; i++) {
						html +="<div style='border-collapse: collapse; border: 1px solid black;' class='detail'>";
						html +="	<table>                                                      ";
						html +="			<tr>";
						html +="				<td>";
						html +="					<input type='hidden' value='"+data.lists[0].medichart_vo[i].medi_num+"' class='medi_num'>";
						html +="				</td>";
						html +="			</tr>";
						html +="			<tr>                                                 ";
						html +="				<th>반려동물명</th>                              ";
						html +="				<td>"+data.lists[0].pet_name+"</td>                       ";
						html +="			</tr>                                                ";
						html +="			<tr>                                                 ";
						html +="				<th>진료기록명</th>                              ";
						html +="				<td>"+data.lists[0].medichart_vo[i].medi_title+"</td>                     ";
						html +="			</tr>                                                ";
						html +="			<tr>                                                 ";
						html +="				<th>진료날짜</th>                                ";
						html +="				<td>"+data.lists[0].medichart_vo[i].medi_visit+"</td>                     ";
						html +="			</tr>                                                ";
						html +="	</table>                                                     ";
					    html +="</div>                                                           ";
					}
					chartPart.innerHTML = html;
				}
				
			},
			error:function(error){
				console.log("값 전달 오류");
			}
		});
		
	}
	
	window.onload = function(){
		var detail = document.querySelectorAll(".detail");
		detail.forEach(function(div){
			div.addEventListener("click", function(){
				console.log("상세보기 실행");
				
				var medi_num = this.querySelector(".medi_num").value;
				console.log(medi_num);
				
				$.ajax({
					url:'./selectOneChart.do',
					method:'post',
					data : {medi_num : medi_num},
					success:function(data){
						console.log("전달받은 리스트 : ",data);
						console.log("반려동물명 : " ,data.pet_name);
						console.log("진료기록명 : ", data.medichart_vo[0].medi_title);
						var modal = document.getElementById("modal");
						
						var html = "";
						    html+"<div class='modal fade' id='detail' role='dialog'>                                          ";
							html+"    <div class='modal-dialog modal-sm'>                                                     ";
							html+"      <div class='modal-content'>                                                           ";
							html+"        <div class='modal-header'>                                                          ";
							html+"          <h4>진료기록 상세보기</h4>                                                        ";
							html+"        </div>                                                                              ";
							html+"        <div class='modal-body'>                                                            ";
							html+"				<table>                                                                       ";
							html+"					<tr>                                                                      ";
							html+"						<th>반려동물명</th>                                                   ";
							html+"						<td>"+data.pet_name+"</td>                                                             ";
							html+"					</tr>                                                                     ";
							html+"					<tr>                                                                      ";
							html+"						<th>진료기록명</th>                                                   ";
							html+"						<td>"+data.medichart_vo[0].medi_title+"</td>                                                             ";
							html+"					</tr>                                                                     ";
							html+"					<tr>                                                                      ";
							html+"						<th>진료일자</th>                                                     ";
							html+"						<td>"+data.medichart_vo[0].medi_visit+"</td>                                                             ";
							html+"					</tr>                                                                     ";
							html+"					<tr>                                                                      ";
							html+"						<th>진료과목</th>                                                     ";
							html+"						<td>"+data.medichart_vo[0].medi_l+"</td>                                                             ";
							html+"					</tr>                                                                     ";
							html+"					<tr>                                                                      ";
							html+"						<th>질환</th>                                                         ";
							html+"						<td>"+data.medichart_vo[0].medi_s+"</td>                                                             ";
							html+"					</tr>                                                                     ";
							html+"					<tr>                                                                      ";
							html+"						<th>진료내용</th>                                                     ";
							html+"						<td>"+data.medichart_vo[0].medi_content+"</td>                                                             ";
							html+"					</tr>                                                                     ";
							html+"				</table>                                                                      ";
							html+"				<button>pdf 다운로드</button>                                                 ";
							html+"        </div>                                                                              ";
							html+"        <div class='modal-footer'>                                                          ";
							html+"          <button type='button' class='btn btn-danger' data-dismiss='modal'>Close</button>  ";
							html+"        </div>                                                                              ";
							html+"     </div>                                                                                 ";
							html+"   </div>                                                                                   ";
							html+" </div>                                                                                     ";
						                                                                
						  modal.append(html);
					},
					error:function(error){
						console.log("값 전달 오류");
					}
					
				});
				
			});
		});
		
	}