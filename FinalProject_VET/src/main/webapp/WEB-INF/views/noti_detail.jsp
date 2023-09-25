<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 - 상세</title>
</head>
<%@ include file="./header.jsp" %>
<body>
<%-- ${noticeDetail} --%>
<%-- ${loginVo} --%>
<script type="text/javascript">
	function del(val){
		location.href="./deleteNotice.do?noti_seq="+val;
	}
	
	function modi(val){
		location.href="./modifyNotice.do?noti_seq="+val;
	}
</script>
	<div id="container">
		<form>
		<table>
			<tr>
				<th>작성일</th>
				<td>${noticeDetail.noti_regdate}</td>
			</tr>
			<tr>
				<th>글 제목</th>
				<td>${noticeDetail.noti_title}</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>관리자</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${noticeDetail.noti_content}</td>
			</tr>
		</table>
		<c:if test="${loginVo.users_auth eq 'A' }">
			<input type="button" value="수정" onclick="modi('${noticeDetail.noti_seq}')">
			<input type="button" value="삭제" onclick="del('${noticeDetail.noti_seq}')">
		</c:if>
		</form>
	</div>

</body>
<%@ include file="./footer.jsp" %>
</html>