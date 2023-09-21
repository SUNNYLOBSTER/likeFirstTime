<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 게시글 작성 화면</title>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<link rel="stylesheet" href="./css/ckeditor.css">
<link rel="stylesheet" href="./css/index.css">
<%@ include file="./header.jsp" %>
</head>
<body data-editor="ClassicEditor" data-collaboration="false" data-revision-history="false">
<div id="container">
	<form action="./qst_writeQuest.do" method="post">
	<!-- 카테고리 선택 -->
	<select id="aCode" name="aCode" class="selectPart" >
		<option disabled="disabled" selected="selected" >--동물 종을 선택하세요--</option>
		<option value="d">개</option>
		<option value="c">고양이</option>
		<option value="r">파충류</option>
		<option value="b">조류</option>
		<option value="f">어류</option>
		<option value="o">기타</option>
	</select>
	<input type="hidden" id="codeInput">
	<select id="aPart" name="aPart" class="selectPart">
		<option disabled="disabled" selected="selected" >--증상이 있는 부위를 선택하세요--</option>
		<option value="01">피부</option>
		<option value="02">눈</option>
		<option value="03">치아</option>
		<option value="04">위,장</option>
		<option value="05">신장,방광</option>
		<option value="06">뼈,관절</option>
		<option value="07">심장</option>
		<option value="08">간</option>
		<option value="09">면역력,호흡기</option>
		<option value="10">기타</option>
	</select>
	<input type="hidden" id="partInput">
	
	<!-- 우선답변 게시글 활성화 -->
	<input type="radio">우선답변 게시글<br>

	<!-- 제목 작성 -->
	<input type="text" name="questTitle" style="width:100%;" required>
	
	<!--  내용 작성 -->
	<textarea id="editor" name="questContent" required></textarea>
	
	<!-- 완료 버튼 -->
	<button type="submit" onclick="writeSubmit()">입력</button>
	<button onclick="history.back(-1)">취소</button>

	</form>
</div>
</body>
<script type="text/javascript" src="./lib/ckeditor5-39.0.1/build/ckeditor.js"></script>
<script type="text/javascript" src="./js/ckeditor.js"></script>
<%@ include file="./footer.jsp" %>
<script type="text/javascript" src="./js/qst_writeQuest.js"></script>
</html>