<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Email 인증 페이지</title>
<script type="text/javascript">
	function certEmail(){
		var users_id = document
		
	}

</script>
</head>
<body>
	<h3>이메일 인증받기</h3>
	<br>
	<input type="button" onclick="certEmail()" value="인증번호 발송"><br><br>
	이메일로 받으신 인증번호를 입력해주세요<br><br>
	<input type="text" id="emailCert"><br><br>
	<input type="button" onclick="self.close()" value="닫기"><br>
	<p id="info"></p>
</body>
</html>