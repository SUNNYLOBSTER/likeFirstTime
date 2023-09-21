<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입페이지_Step03</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
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
		반려인: <input name="pet_owner" id="pet_owner" value="${signUpVo.users_id}"><br>
		이름: <input name="pet_name" id="pet_name"><br>
		생일: <input name="pet_bday" id="pet_bday" placeholder="ex)20220505"><br>
		
		<!-- selectbox로 만들기 -->
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