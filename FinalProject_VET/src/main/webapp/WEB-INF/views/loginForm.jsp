<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
<style type="text/css">
	#page{
		width: 500px;
		margin: 10px auto;
	}
</style>
</head>
<body>
	<div id="page">
		<h1>로그인</h1>
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
					<input type="button" value="회원가입">
					<input type="button" value="아이디 찾기">
					<input type="button" value="비밀번호 찾기">
					</td>
				</tr>	
			</table>
		</form>
	</div>
</body>
</html>