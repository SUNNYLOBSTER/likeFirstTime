<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입페이지_Step02(병원)</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="./js/users_insertHospStepTwo.js"></script>
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
	#chk{
		width: auto;
		height: auto;
	}
</style>
</head>
<%@ include file="./header.jsp" %>
<body>

<div id="container">
 <form action="./signUpHosp.do" method="post">
	<h2>회원가입</h2>
 	<input name="users_id" id="users_id" placeholder="메일을 입력해주세요" onclick="return duplicateId()">
 	<br>
 	<input type="password" name="users_pw" id="users_pw" placeholder="비밀번호를 입력해주세요" onchange="isSame()">
 	<br>
 	<input type="password" id="users_pwOk" placeholder="비밀번호를 한 번 더 입력해주세요" onchange="isSame()">&nbsp;&nbsp;<span id="checkPw"></span>
 	<br>
 	<input name="users_name" id="users_name" placeholder="병원 이름을 입력해주세요">
 	<br>
 	<input name="users_tel" id="users_tel" placeholder="전화번호를 입력해주세요 ex) 01011112222" onchange="checkTel()">
 	<br>
 	<input name="users_crn" id="users_crn" placeholder="사업자등록번호를 입력해주세요" onchange="checkCrn()">
 	<br>
 	<input type="text" id="sample6_postcode" placeholder="우편번호 찾기를 통해 우편번호를 입력해주세요">
	<input type="button" id="fixButton" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
	<input type="text" name="addr" id="sample6_address" placeholder="주소는 자동으로 입력됩니다"><br>
	<input type="text" name="addrDetail" id="sample6_detailAddress" placeholder="상세주소를 입력해주세요">
	<input type="hidden" id="sample6_extraAddress" placeholder="참고항목">
 	<br>
 	
 	<div style="margin: 10px auto;">
 	<span>운영시간을 선택해주세요</span><br>
 	<select name="hosp_openTime" id="hosp_openTime">
 		<option value="">--여는 시간--</option>
 		<option value="00">오전 0시</option>
 		<option value="01">오전 1시</option>
 		<option value="02">오전 2시</option>
 		<option value="03">오전 3시</option>
 		<option value="04">오전 4시</option>
 		<option value="05">오전 5시</option>
 		<option value="06">오전 6시</option>
 		<option value="07">오전 7시</option>
 		<option value="08">오전 8시</option>
 		<option value="09">오전 9시</option>
 		<option value="10">오전 10시</option>
 		<option value="11">오전 11시</option>
 		<option value="12">오후 12시</option>
 		<option value="13">오후 1시</option>
 		<option value="14">오후 2시</option>
 		<option value="15">오후 3시</option>
 		<option value="16">오후 4시</option>
 		<option value="17">오후 5시</option>
 		<option value="18">오후 6시</option>
 		<option value="19">오후 7시</option>
 		<option value="20">오후 8시</option>
 		<option value="21">오후 9시</option>
 		<option value="22">오후 10시</option>
 		<option value="23">오후 11시</option>
 	</select>
 	<select name="hosp_closeTime" id="hosp_closeTime">
 		<option value="">--닫는 시간--</option>
 		<option value="24">오전 0시</option>
 		<option value="01">오전 1시</option>
 		<option value="02">오전 2시</option>
 		<option value="03">오전 3시</option>
 		<option value="04">오전 4시</option>
 		<option value="05">오전 5시</option>
 		<option value="06">오전 6시</option>
 		<option value="07">오전 7시</option>
 		<option value="08">오전 8시</option>
 		<option value="09">오전 9시</option>
 		<option value="10">오전 10시</option>
 		<option value="11">오전 11시</option>
 		<option value="12">오후 12시</option>
 		<option value="13">오후 1시</option>
 		<option value="14">오후 2시</option>
 		<option value="15">오후 3시</option>
 		<option value="16">오후 4시</option>
 		<option value="17">오후 5시</option>
 		<option value="18">오후 6시</option>
 		<option value="19">오후 7시</option>
 		<option value="20">오후 8시</option>
 		<option value="21">오후 9시</option>
 		<option value="22">오후 10시</option>
 		<option value="23">오후 11시</option>
 	</select>
 	</div>
 	
 	<div style="margin: 10px auto;">
 	<span>휴일을 선택해주세요</span><br>
 	<input type="checkbox" name="chk" id="chk" value="일">일요일<br>
 	<input type="checkbox" name="chk" id="chk" value="월">월요일<br>
 	<input type="checkbox" name="chk" id="chk" value="화">화요일<br>
 	<input type="checkbox" name="chk" id="chk" value="수">수요일<br>
 	<input type="checkbox" name="chk" id="chk" value="목">목요일<br>
 	<input type="checkbox" name="chk" id="chk" value="금">금요일<br>
 	<input type="checkbox" name="chk" id="chk" value="토">토요일<br>
 	</div>
 	
 	<div style="margin: 10px auto;">
 	<span>주차 가능 여부를 선택해주세요</span>
	<select name="hosp_park" id="hosp_park">
 		<option value="">--주차 여부--</option>
 		<option value="Y">주차 가능</option>
 		<option value="N">주차 불가능</option>
 	</select>
 	</div>
 	
 	<div style="margin: 10px auto;">
 	<span>운영 형태를 선택해주세요</span>
 		<select name="hosp_mng" id="hosp_mng">
 		<option value="">--운영 형태--</option>
 		<option value="G">일반 병원</option>
 		<option value="E">24시 병원</option>
 		<option value="S">2차 병원</option>
 	</select>
 	</div>
 	
 	<input type="text" name="hosp_etc" id="hosp_etc" placeholder="기타 소개 사항을 작성해주세요">
 	 	 
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