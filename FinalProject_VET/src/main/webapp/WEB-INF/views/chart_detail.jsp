<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>진료기록 상세조회</title>
</head>
<%@ include file="./header.jsp" %>
<body>
	<div id="container">
	${pvo}<br>
	${medi_content}
		<table style="border-collapse: collapse; border: 1px solid black;">
			<tr>
				<th>반려동물</th>
				<td>${pvo.pet_name}</td>
			</tr>
			<tr>
				<th>진료일자</th>
				<td>${pvo.medichart_vo[0].medi_visit}</td>
			</tr>
			<tr>
				<th>진료과목</th>
				<td>${pvo.medichart_vo[0].medi_lname}</td>
			</tr>
			<tr>
				<th>세부 진료과목</th>
				<td>${pvo.medichart_vo[0].medi_sname}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${pvo.medichart_vo[0].medi_title}</td>
			</tr>
		</table>
		<div id="content" class="ck-content">
			${medi_content}
		</div>
		<div>
			<input type="hidden" value="${pvo.medichart_vo[0].medi_num}" name="medi_num">
			<button onclick="location.href='./modifyChartForm.do?medi_num=${pvo.medichart_vo[0].medi_num}'">수정</button>
			<button onclick="location.href='./deleteChart.do?medi_num=${pvo.medichart_vo[0].medi_num}'">삭제</button>
			<button onclick="location.href='./selectAllChart.do'">전체목록보기</button>
		</div>
	</div>	
</body>
<%@ include file="./footer.jsp" %>
</html>