<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 - 상세</title>
<link rel="stylesheet" href="./css/noti_list.css">
<style type="text/css">
	input[type="button"]{
		background-color: #D7CCC8;
		width: 110px;
		height: 20px;
		border-radius: 5px;
		color: #3E2723;
		border: none;
		box-shadow: 1.5px 1.5px 1.5px 0 #3E2723;
	}
	
	#inputButton{
		text-align: center;
	}
</style>
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
		<table id="chartInfo">
			<tr>
				<th>제목</th>
				<td>${noticeDetail.noti_title}</td>
				<th>작성일</th>
				<td>${noticeDetail.noti_regdate}</td>
				<th>작성자</th>
				<td>관리자</td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td colspan="2" style="text-align: left;">${noticeDetail.noti_content}</td>
			</tr>
		</table>
		<c:if test="${loginVo.users_auth eq 'A' }">
			<div id=inputButton>
			<input type="button" value="수정" onclick="modi('${noticeDetail.noti_seq}')">
			<input type="button" value="삭제" onclick="del('${noticeDetail.noti_seq}')">
			</div>
		</c:if>
		</form>
	</div>

</body>
<%@ include file="./footer.jsp" %>
</html>