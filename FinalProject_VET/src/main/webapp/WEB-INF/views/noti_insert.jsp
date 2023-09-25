<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 - 새 글 작성</title>
<%@ include file="./header.jsp" %>
<script type="text/javascript">
	function regdate (){
		var today = document.getElementById("noti_regdate");
		var date = new Date();
		console.log(date);
		today.innerHTML = date.toLocaleDateString();
	}
</script>
</head>
<body>
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
				<tr>
					<th>내용</th>
					<td><input type="text" name="noti_content" required="required"> </td>
				</tr>
			</table>
			<input type="hidden" name="noti_id" value="${loginVo.users_id}">
			<c:if test="${loginVo.users_auth eq 'A' }">
				<input type="submit" value="작성">
			</c:if>
			</form>
		</div>
</body>
<%@ include file="./footer.jsp" %>
</html>