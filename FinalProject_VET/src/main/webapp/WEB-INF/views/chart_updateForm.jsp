<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>진료기록 수정</title>
<link rel="stylesheet" href="./css/ckeditor.css">
<link rel="stylesheet" href="./css/index.css">
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
</head>
<%@ include file="./header.jsp" %>
<body data-editor="ClassicEditor" data-collaboration="false" data-revision-history="false">
	<div id="container">
		<h1>진료기록 수정</h1>
			<table style="border-collapse: collapse; border: 1px solid black;">
					<tr>
						<th>반려동물</th>
						<td>${pvo.pet_name}
						</td>
					</tr>
					<tr>
						<th>진료일자</th>
						<td>${pvo.medichart_vo[0].medi_visit}
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td>${pvo.medichart_vo[0].medi_title}
						</td>
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
				
				<form action="./updateChart.do" method="post">
					<input type="hidden" id="medi_num" name="medi_num" value="${pvo.medichart_vo[0].medi_num}">
					<textarea name="medi_content" id="editor">${medi_content}</textarea>
				</form>
				<div>
					<input type="button" onclick="updateBoard()" value="수정하기">
				</div>
		
	</div>
<script type="text/javascript" src="./lib/ckeditor5-39.0.1/build/ckeditor.js"></script>
<script type="text/javascript" src="./js/ckeditor.js"></script>
<script type="text/javascript" src="./js/chart_updateForm.js"></script>
</body>
<%@ include file="./footer.jsp" %>
</html>