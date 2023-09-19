<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<%@ include file="./header.jsp" %>
<body>
	<div id="container">
		<form action="./login.do" method="post">
			<table>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="users_id">
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input type="password" name="users_pw">
					</td>
				</tr>	
				<tr>
					<td colspan="2">
					<input type="submit" value="로그인">
					<input type="button" value="회원가입" onclick="location.href='./insertUsers.do'">
					<input type="button" value="아이디 찾기" onclick="location.href='./findId.do'">
					<input type="button" value="비밀번호 찾기" onclick="location.href='./findPasword.do'">
					</td>
				</tr>	
			</table>
		</form>
	</div>
</body>
<%@ include file="./footer.jsp" %>
</html>