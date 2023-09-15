//진료코드 대분류 선택에 따른 소분류 <option>태그 생성
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

          html +="<option>--선택--</option>";

          for(let i=0; i<data.list.length ;i++ ){
					 	html += "<option value='"+data.list[i].medi_code+"'>"+data.list[i].medi_name+"</option>";
						obj.append(html);
					}
					obj.empty().append(html);
				},
				error:function(){
					console.log("값 전달 오류");
				}
			});
		}
	
	//반려동물 seq선택에 따른 진료기록 조회 및 출력
	$(document).on("click",".selectPet", function(){
		console.log("selectPet 실행");
		var petValue = $(this).val();
		console.log("선택된 pet_seq : ", petValue);
		
		$.ajax({
			url:'selectPetChart.do',
			method:'post',
			data:{pet_seq : petValue},
			success:function(data){
				console.log("전달받은 리스트 : ", data);
				
				var listname = document.getElementById('listname');
				listname.innerHTML = '<p>반려동물별 진료기록</p>';
				if(data.detail.length == 0){
				var chartPart = document.getElementById('chartPart');
				chartPart.innerHTML = '<div>일치하는 진료기록이 없습니다</div>';	
				}else{
					var chartPart = document.getElementById('chartPart');
					chartPart.innerHTML = '';
					var html = "";
					for (var i = 0; i < data.detail[0].medichart_vo.length; i++) {
						html +="<div style='border-collapse: collapse; border: 1px solid black;' class='detail'>";
						html +="	<table>                                                      ";
						html +="			<tr>";
						html +="				<td>";
						html +="					<input type='hidden' value='"+data.detail[0].medichart_vo[i].medi_num+"' class='medi_num'>";
						html +="				</td>";
						html +="			</tr>";
						html +="			<tr>                                                 ";
						html +="				<th>반려동물명</th>                              ";
						html +="				<td>"+data.detail[0].pet_name+"</td>                       ";
						html +="			</tr>                                                ";
						html +="			<tr>                                                 ";
						html +="				<th>진료기록명</th>                              ";
						html +="				<td>"+data.detail[0].medichart_vo[i].medi_title+"</td>                     ";
						html +="			</tr>                                                ";
						html +="			<tr>                                                 ";
						html +="				<th>진료날짜</th>                                ";
						html +="				<td>"+data.detail[0].medichart_vo[i].medi_visit+"</td>                     ";
						html +="			</tr>                                                ";
						html +="	</table>                                                     ";
					    html +="</div>                                                           ";
					}
					chartPart.innerHTML = html;
				}
			},
			error:function(){
				console.log("값 전달 오류");
			}
		});
		
	});
	
	//진료기록 대분류,소분류 선택에 따른 진료기록 조회 및 출력
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
			error:function(){
				console.log("값 전달 오류");
			}
		});
		
	}
	
	//진료기록 <div> 클릭시 상세보기 페이지 호출
	$(document).on("click", ".detail", function(){
		console.log("상세보기 실행");
		
		var medi_num = this.querySelector(".medi_num").value;
		console.log("진료번호 : " ,medi_num);
				
		location.href='./selectOneChart.do?medi_num='+medi_num;
		
	});