<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>진료기록 상세조회</title>
</head>
<body>
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
			<th>제목</th>
			<td>${pvo.medichart_vo[0].medi_title}</td>
		</tr>
		<tr>
			<th>진료내용</th>
			<td>${pvo.medichart_vo[0].medi_content}</td>
		</tr>
		<tr>
			<th>진료과목</th>
			<td>${pvo.medichart_vo[0].medi_lname}</td>
		</tr>
		<tr>
			<th>세부 진료과목</th>
			<td>${pvo.medichart_vo[0].medi_sname}</td>
		</tr>
	</table>
</body>
</html>