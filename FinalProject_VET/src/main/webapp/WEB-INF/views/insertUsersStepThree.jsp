<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입페이지_Step03</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
        }
    }).open();
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
<form action="./addInfo.do" method="post">
	<h4>추가정보 입력</h4>
	<div>
	 주소: <input name="users_addr" id="users_addr" placeholder="주소를 입력해주세요" onclick="return findAddr()">
	 
	<input type="text" id="sample6_postcode" placeholder="우편번호">
	<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
	<input type="text" id="sample6_address" placeholder="주소"><br>
	<input type="text" id="sample6_detailAddress" placeholder="상세주소">
	<br>
	 추가전화번호: <input name="users_subtel" id="users_subtel" placeholder="ex)01011112222">
	 </div>
	 <br>
	 <h4>반려동물 정보</h4>
	<div>
		반려인: <input name="pet_owner" id="pet_owner" value="${signUpVo.users_id}"><br>
		이름: <input name="pet_name" id="pet_name"><br>
		생일: <input name="pet_bday" id="pet_bday" placeholder="ex)20220505"><br>
		종류: <input name="pet_species" id="pet_species"><br>
		성별: <input name="pet_gender" id="pet_gender"><br>
		중성화 여부: <input name="pet_neut" id="pet_neut"><br>
		특이사항: <input name="pet_report" id="pet_report"><br>
	</div>
</form>
</div>

</body>
<%@ include file="./footer.jsp" %>
</html>