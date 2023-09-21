<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입페이지_Step03</title>
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
<h3>추가 정보를 입력하시면 더 많은 서비스를 경험하실 수 있습니다.</h3>
<button onclick="location.href='./main.do'" id="nextTime">다음에 할게요</button>
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
	<br>
	 추가전화번호: <input name="users_subtel" id="users_subtel" placeholder="ex)01011112222">
	 </div>
	 <br>
	 <h4>반려동물 정보</h4>
	<div>
		<input type="hidden" name="pet_owner" id="pet_owner" value="${signUpVo.users_id}"><br>
		이름: <input name="pet_name" id="pet_name"><br>
		생일: <input name="pet_bday" id="pet_bday" placeholder="ex)20220505"><br>
		종류: 
		<select name="pet_species" id="pet_species">
			<option value="">--선택--</option>
			<option value="D">개</option>
			<option value="C">고양이</option>
			<option value="R">파충류</option>
			<option value="B">조류</option>
			<option value="F">어류</option>
			<option value="O">기타</option>
		</select><br>
		성별: 
		<select name="pet_gender" id="pet_gender">
			<option value="M">--선택--</option>
			<option value="M">수</option>
			<option value="F">암</option>
		</select> <br>
		중성화 여부:
		<select name="pet_neut" id="pet_neut">
			<option value="Y">--선택--</option>
			<option value="Y">완료</option>
			<option value="N">미완료</option>
		</select><br>
		특이사항: <input name="pet_report" id="pet_report"><br>
		<input type="submit" value="추가정보 등록" onclick="return addinfo()">
	</div>
</form>
</div>

</body>
<%@ include file="./footer.jsp" %>
</html>