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
			<c:forEach var="codelist" items="${codelists}" varStatus="vs">
				<option value="${codelist.medi_code}">${codelist.medi_name}</option>			
			</c:forEach>
		</select>
		<select id="codeL2">
			
							
			
		</select>
		<button id="selectMediCode">조회</button>
	<hr>
	<h1>일반사용자별 진료기록 전체리스트</h1>
	${allCharts}<br>
<button onclick="window.history.back()">전체목록으로 돌아가기</button>



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
				console.log(data.name.length);
// 				 var obj = document.getElementById("codeL2");
				var obj = $("#codeL2");
				for(let i=0; i<data.name.length ;i++ ){
					var html = "<option value='"+i+"'>"+i+"</option>";
					obj.append(html);
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

