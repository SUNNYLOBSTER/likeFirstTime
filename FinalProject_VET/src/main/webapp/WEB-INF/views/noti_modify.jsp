<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 - 수정</title>
<link rel="stylesheet" href="./css/ckeditor.css">
<link rel="stylesheet" href="./css/index.css">
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
</head>
<%@ include file="./header.jsp" %>
<script type="text/javascript">
	function del(val){
		location.href="./deleteNotice.do?noti_seq="+val;
	}
	
	function modi(val){
		location.href="./modifyNotice.do?noti_seq="+val;
	}
</script>
<body data-editor="ClassicEditor" data-collaboration="false" data-revision-history="false">
<%-- ${loginVo} --%>
<%-- ${noticeDetail} --%>
<div id="container">
	<br>
	<h3 style="text-align: left;">공지사항 수정</h3>
	<br>
	<form action="./modifyNotice.do" method="post">
		<table>
			<tr>
			<th>제목</th>
				<td> &nbsp;&nbsp; <input type="text" name="noti_title" value="${noticeDetail.noti_title}" required="required"></td>
			</tr>
			<tr>
			<th>작성일</th>
				<td> &nbsp;&nbsp; <input type="text" name="noti_regdate" value="${noticeDetail.noti_regdate}" readonly="readonly"></td>
			</tr>
			<tr>
			<th>작성자</th>
				<td> &nbsp;&nbsp; <input type="text" value="작성자" readonly="readonly"></td>
			</tr>
		</table>
		<br>
		<textarea name="noti_content" id="editor" required="required">${noticeDetail.noti_content}</textarea>
		<input type="hidden" name="noti_seq" value="${noticeDetail.noti_seq}">
		<input type="submit" value="수정">
	</form>
</div>
<script type="text/javascript" src="./lib/ckeditor5-39.0.1/build/ckeditor.js"></script>
<script type="text/javascript" src="./js/ckeditor.js"></script>
</body>
<%@ include file="./footer.jsp" %>
</html>