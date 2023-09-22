<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<%@ include file="./header.jsp" %>
<body>
	<div id="container">
		<div style="display: flex; justify-content: center; align-items: center; margin-top: 50px;">
		<form action="./login.do" method="post">
			<table>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="users_id" required="required">
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input type="password" name="users_pw" required="required">
					</td>
				</tr>	
				<tr>
					<td colspan="2">
					<input type="submit" value="로그인">
					<input type="button" value="회원가입" onclick="location.href='./insertUsers.do'">
					<input type="button" value="아이디 찾기" onclick="location.href='./findId.do'">
					<input type="button" value="비밀번호 찾기" onclick="location.href='./findPasword.do'">
					<div id="naver_id_login" style="text-align: right; margin-top: 3px;"></div>
					</td>
				</tr>
			</table>
		</form>
		</div>
		
    <!-- //네이버 로그인 버튼 노출 영역 -->
    
    <script type="text/javascript">
        var naver_id_login = new naver_id_login("VULq_T6rjXHVJh5qCkut", "http://localhost:8080/FinalProject_VET/main.do");
        var state = naver_id_login.getUniqState();
        
        naver_id_login.setButton("green", 3 , 40);
        naver_id_login.setState(state);
        
        naver_id_login.setPopup();
        naver_id_login.init_naver_id_login();
        
        var naver_id_login = new naver_id_login("YOUR_CLIENT_ID", "YOUR_CALLBACK_URL");
        // 접근 토큰 값 출력
        alert(naver_id_login.oauthParams.access_token);
        // 네이버 사용자 프로필 조회
        naver_id_login.get_naver_userprofile("naverSignInCallback()");
        // 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
        function naverSignInCallback() {
          alert(naver_id_login.getProfileData('email'));
          alert(naver_id_login.getProfileData('nickname'));
          alert(naver_id_login.getProfileData('age'));
        }
        
        
    </script>
    
    
	</div>
</body>
<%@ include file="./footer.jsp" %>
</html>