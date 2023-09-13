<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>전체 진료기록 리스트</title>
</head>
<body>
<c:choose>
	<c:when test="${empty allPets}">
		<p>등록된 반려동물 정보가 없습니다</p>
	</c:when>
	<c:otherwise>
		<c:forEach var="pet" items="${allPets}" varStatus="vs">
			${vs.count} : <button onclick="location.href='selectPetChart.do?pet_name=${pet.pet_name}'"><c:out value="${pet.pet_name}"/></button><br>
		</c:forEach>
	</c:otherwise>
</c:choose><br>
	<button>반려동물 등록하기</button>
	
	<hr>
		<select id="codeL" onchange="codeLChange()">
			<option>--진료과목--</option>
			<c:forEach var="codelist" items="${codelists}" varStatus="vs">
				<option value="${codelist.medi_code}">${codelist.medi_name}</option>			
			</c:forEach>
		</select>
		<select id="codeS">
			<option>--질환--</option>
		</select>
		<button id="selectMediCode" onclick="selectCode()">조회</button>
		
	<hr>
	
	<button onclick="location.href='./insertNewChartForm.do'">새 진료기록 작성</button>
	
	<hr>
	
	<h1 id="listname">진료기록 전체목록</h1>
	<div id="chartPart" style="border-collapse: collapse; border: 1px solid black;">
		<c:choose>
			<c:when test="${empty allCharts}">
				<p>등록된 진료기록이 없습니다</p>
			</c:when>
			<c:otherwise>
				<c:forEach var="clist" items="${allCharts}" varStatus="vs">
					<c:forEach var="mlist" items="${clist.medichart_vo}">
							<div style="border-collapse: collapse; border: 1px solid black;">
								<table>
										<tr>
											<th>반려동물명</th>
											<td>${clist.pet_name}</td>
										</tr>
										<tr>
											<th>진료기록명</th>
											<td>${mlist.medi_title}</td>
										</tr>
										<tr>
											<th>진료날짜</th>
											<td>${mlist.medi_visit}</td>
										</tr>
								</table>
							</div>
					</c:forEach>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
<button onclick="window.history.back()">뒤로가기</button>



<script type="text/javascript">
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
						html +="<div style='border-collapse: collapse; border: 1px solid black;'>";
						html +="	<table>                                                      ";
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
</script>
</body>
</html>

