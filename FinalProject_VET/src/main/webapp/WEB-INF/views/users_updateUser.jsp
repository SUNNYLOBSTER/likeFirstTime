<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정(일반사용자)</title>
<style type="text/css">
	input[type="submit"]{
		float: right;
	}
</style>
</head>
<body>
<%@ include file="./navbar.jsp" %>
<div class="navContainer">
${loginVo}
${lists}
<hr>
	<h3>내 정보 수정</h3>
	<form action="./updateUser.do" method="post">
		<table>
			<tr>
			<th>ID</th>
				<td> &nbsp;&nbsp; <input type="text" name="users_id" value="${lists[0].users_id}" readonly="readonly"></td>
			</tr>
			<tr>
			<th>이름</th>
				<td> &nbsp;&nbsp; <input type="text" name="users_name" value="${lists[0].users_name}" required="required"></td>
			</tr>
			<tr>
			<th>전화번호</th>
				<td> &nbsp;&nbsp; <input type="text" name="users_tel" value="${lists[0].users_tel}" required="required"></td>
			</tr>
			<tr>
			<th>주소</th>
				<td> &nbsp;&nbsp; <input type="text" name="users_addr" value="${lists[0].users_addr}"></td>
			</tr>
			<tr>
			<th>가입일</th>
				<td> &nbsp;&nbsp; <input type="text" name="users_joindate" value="${lists[0].users_joindate}" readonly="readonly"></td>
			</tr>
			<tr>
			<th>추가 연락처</th>
				<td> &nbsp;&nbsp; <input type="text" name="users_subtel" value="${lists[0].users_subtel}"></td>
			</tr>
		</table>
		<br>
		<input type="submit" value="수정">
	</form>

</div>


</body>
<%@ include file="./footer.jsp" %>
</html>