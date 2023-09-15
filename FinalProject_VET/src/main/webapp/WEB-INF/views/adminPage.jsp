<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script type="text/javascript" src="./js/adminPage.js"></script>
</head>
<body>
${loginVo}
<hr>
<h2>관리자 페이지 입니다.</h2>
<%-- ${listsVo} --%>
<select name="userAuth" id="userAuth" onchange="selectAuth()">
	<option value="">권한</option>
	<option value="U">일반사용자</option>
	<option value="H">병원관계자</option>
	<option value="A">관리자</option>
</select>

<select name="userStatus" id="userStatus" onchange="selectStatus()">
	<option value="">상태</option>
	<option value="N">활동중</option>
	<option value="S">활동중지</option>
	<option value="Y">탈퇴</option>
</select>

<input type="text" name="keyword" id="keyword">
<input type="button" onclick="searchUserId()" value="검색" id="searchUserId">
<p id="info"></p>

<div id="userList">
<table>
	<thead>
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>활동상태</th>
			<th>권한</th>
			<th>사업자등록번호</th>
			<th>가입일</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="vo" items="${listsVo}" varStatus="vs">
			<tr>
				<td>${vs.count}</td>
				<td><a href="./selectUserDetail.do?users_id=${vo.users_id}">${vo.users_id}</a></td>
				<td>${vo.users_name}</td>
				<td>${vo.users_tel}</td>
				<td>${vo.users_status}</td>
				<td>${vo.users_auth}</td>
				<td>${vo.users_crn}</td>
				<td>${vo.users_joindate}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</div>
</body>
</html>