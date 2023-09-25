<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 - 수정</title>
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
<body>
${loginVo}
${noticeDetail}
<hr>
<div id="container">
	<h3>공지사항 수정</h3>
	<form action="./modifyNotice.do" method="post">
		<table>
			<tr>
			<th>제목</th>
				<td> &nbsp;&nbsp; <input type="text" name="noti_title" value="${noticeDetail.noti_title}" required="required"></td>
			</tr>
			<tr>
			<th>작성자</th>
				<td> &nbsp;&nbsp; <input type="text" value="작성자" readonly="readonly"></td>
			</tr>
			<tr>
			<th>작성일</th>
				<td> &nbsp;&nbsp; <input type="text" name="noti_regdate" value="${noticeDetail.noti_regdate}" readonly="readonly"></td>
			</tr>
			<tr>
			<th>글 내용</th>
				<td> &nbsp;&nbsp; <input type="text" name="noti_content" value="${noticeDetail.noti_content}" required="required"></td>
			</tr>
		</table>
		<br>
		<input type="hidden" name="noti_seq" value="${noticeDetail.noti_seq}">
		<input type="submit" value="수정">
	</form>
</div>
</body>
</html>