<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입페이지_Step03(병원)</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="./js/users_insertUsersStepThree.js"></script>
<script>
   
</script>
</head>
<%@ include file="./header.jsp" %>

<style type="text/css">
	h2{
		text-align: center;
	}
	h3 {
		text-align: center;
	}
</style>
<body>

<div id="container">
${signUpVo}
<h2>가입을 축하드립니다!</h2>
<h3>로그인 후 사이트를 이용해보세요</h3>
<button onclick="location.href='./loginForm.do'" id="goToQuestBoard">로그인 하기</button>
<form action="./addUserInfo.do" method="post">
	<h4>추가정보 입력</h4>
	<div>
	<input type="hidden" name=users_id value="${signUpVo.users_id}">
	 주소:
	 <!-- 화면에서 넘길 때 일반주소 상세주소 합쳐서 넘기기 --> 	 
	<input type="text" id="sample6_postcode" placeholder="우편번호">
	<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
	<input type="text" name="addr" id="sample6_address" placeholder="주소"><br>
	<input type="text" name="addrDetail" id="sample6_detailAddress" placeholder="상세주소">
	<input type="hidden" id="sample6_extraAddress" placeholder="참고항목">
	
</form>
</div>

</body>
<%@ include file="./footer.jsp" %>
</html>