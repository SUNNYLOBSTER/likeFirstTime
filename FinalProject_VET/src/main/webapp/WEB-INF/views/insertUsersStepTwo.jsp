<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입페이지_Step02</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script type="text/javascript" src="./js/users_insertUsersStepTwo.js"></script>
<script type="text/javascript">
	function duplicateId(){
	console.log("아이디 중복검사 함수");
	window.open("./duplication.do","중복검사", "width=300px, height=300px");
}
</script>
<style type="text/css">
	input {
		width: 400px;
		height: 20px;
		margin: 3px;
	}
	#fixButton{
		width: auto;
		height: auto;
		padding-block: 1px;
    	padding-inline: 6px;
	}
</style>
</head>
<%@ include file="./header.jsp" %>
<body>

<div id="container">
 <form action="./signUp.do" method="post">
	<h2>회원가입</h2>
	<div> 	
 	<input name="users_id" id="users_id" placeholder="메일을 입력해주세요" onclick="return duplicateId()" required="required">
 	<br>
 	<input type="password" name="users_pw" id="users_pw" placeholder="비밀번호를 입력해주세요" onchange="isSame()" required="required">
 	<br>
 	<input type="password" id="users_pwOk" placeholder="비밀번호를 한 번 더 입력해주세요" onchange="isSame()">&nbsp;&nbsp;<span id="checkPw" required="required"></span>
 	<br>
 	<input name="users_name" id="users_name" placeholder="이름을 작성해주세요" required="required">
 	<br>
 	<input name="users_tel" id="users_tel" placeholder="전화번호를 입력해주세요 ex)01011112222" required="required">
 	</div>
 	<br>
 	<div>
	 	<input type="submit" id="fixButton" value="회원가입">
	 	<input type="button" id="fixButton" value="가입 취소" onclick="javascipt:location.href='./main.do'">
 	</div>
  </form>

</div>

</body>
<%@ include file="./footer.jsp" %>
</html>