<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 - 새 글 작성</title>
<link rel="stylesheet" href="./css/ckeditor.css">
<link rel="stylesheet" href="./css/index.css">
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
</head>
<%@ include file="./header.jsp" %>
<body data-editor="ClassicEditor" data-collaboration="false" data-revision-history="false">
	<div id="container">
		<form action="./insertNotice.do" method="post">
			<table>
				<tr>
					<th>글 제목</th>
					<td><input name="noti_title" required="required"></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>관리자</td>
				</tr>
			</table>
			<br>
			<textarea id="editor" name="noti_content"></textarea>
			<input type="hidden" name="noti_id" value="${loginVo.users_id}">
			<c:if test="${loginVo.users_auth eq 'A' }">
				<input type="submit" value="작성">
			</c:if>
			</form>
		</div>
<script type="text/javascript" src="./lib/ckeditor5-39.0.1/build/ckeditor.js"></script>
<script type="text/javascript" src="./js/ckeditor.js"></script>
</body>
<%@ include file="./footer.jsp" %>
</html>