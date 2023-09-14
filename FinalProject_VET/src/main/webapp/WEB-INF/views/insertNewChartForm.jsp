<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 진료기록 작성</title>
<link rel="stylesheet" href="./css/ckeditor.css">
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script type="text/javascript" src="./js/newChartForm.js"></script>
</head>
<body>
	<div id="container">
		<h1>새 진료기록 작성</h1>
		<form action="./insertNewChart.do" method="post">
			진료날짜 : <input type="date" id="medi_visit" name="medi_visit" value="2023-08-19"><br>
			진료과목 : <select id="codeL" onchange="codeLChange()">
						<option id="medi_l">--진료 과목--</option>
						<option value="00">일반진료</option>
						<option value="01">내과</option>
						<option value="02">외과</option>
					</select>
			<select id="codeS">
				<option>--질환--</option>
			</select>
			반려동물명 : <select id="petName">
						<c:forEach var="pets" items="${petList}" varStatus="vs">
							<option value="${pets.pet_seq}" id="mpet_seq">${pets.pet_name}</option>
						</c:forEach>
					</select><br>
			제목 : <input type="text" id="medi_title" maxlength="25">
			<textarea id="editor" name="medi_content"></textarea>
		</form>
	</div>
	
<script type="text/javascript" src="./lib/ckeditor5-39.0.1/build/ckeditor.js"></script>
<script type="text/javascript" src="./js/ckeditor.js"></script> 
<script type="text/javascript" src="./js/index.js"></script> 
<script type="text/javascript">
document.getElementById('medi_visit').value = new Date().toISOString().substring(0, 10);
</script>
</body>
</html>