<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입페이지_Step02(병원)</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script type="text/javascript" src="./js/users_insertHospStepTwo.js"></script>
<script type="text/javascript">
	function duplicateId(){
	console.log("아이디 중복검사 함수");
	window.open("./duplication.do","중복검사", "width=300px, height=300px");
}
</script>
</head>
<%@ include file="./header.jsp" %>
<body>

<div id="container">
 <form action="./signUpHosp.do" method="post">
	<h2>회원가입</h2>
	<div> 	
 	<input name="users_id" id="users_id" placeholder="메일을 입력해주세요" onclick="return duplicateId()">
 	<button id="emailCheckBtn" onclick="return emailCert()">이메일 인증</button>
 	<span id="resultDuplicate"></span>
 	<br>
 	<input class="emailCheckInput" placeholder="인증번호 6자리 입력" disabled="disabled" maxlength="6">
 	<br>
 	<span id="emailCheckWarn"></span>
 	<br>
 	<input type="password" name="users_pw" id="users_pw" placeholder="비밀번호 작성" onchange="isSame()">
 	<br>
 	<input type="password" id="users_pwOk" placeholder="비밀번호 확인" onchange="isSame()">&nbsp;&nbsp;&nbsp;<span id="checkPw"></span>
 	<br>
 	<input name="users_name" id="users_name" placeholder="이름 작성">
 	<br>
 	<input name="users_tel" id="users_tel" placeholder="ex)01011112222">
 	<br>
 	<!-- 카카오주소api -->
 	<input name="users_crn" id="users_crn" placeholder="주소" onchange="return checkCrn()">
 	<br>
 	<!-- 셀렉트박스로 -->
 	<input name="users_crn" id="users_crn" placeholder="영업시간" onchange="return checkCrn()">
 	<br>
 	<input name="users_crn" id="users_crn" placeholder="휴일" onchange="return checkCrn()">
 	<br>
 	<input name="users_crn" id="users_crn" placeholder="사업자등록번호" onchange="return checkCrn()">
 	<br>
 	<input name="users_crn" id="users_crn" placeholder="사업자등록번호" onchange="return checkCrn()">
 	<br>
 	<input name="users_crn" id="users_crn" placeholder="사업자등록번호" onchange="return checkCrn()">
 	<br>
 	<input name="users_crn" id="users_crn" placeholder="사업자등록번호" onchange="return checkCrn()">
 	
 	</div>
 	<br><br>
 	<div>
	 	<input type="submit" value="회원가입">
	 	<input type="button" value="가입 취소" onclick="javascipt:location.href='./main.do'">
 	</div>
  </form>

</div>

</body>
<%@ include file="./footer.jsp" %>
</html>