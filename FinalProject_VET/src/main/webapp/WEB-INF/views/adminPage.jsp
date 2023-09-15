<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<%@ include file="./header.jsp" %>
<script type="text/javascript" src="./js/adminPage.js"></script>
</head>
<body>

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
<form action="./mutiChange.do" method="post" id="changeStatus" name="changeStatus" onsubmit="return chkBox()">
<input type="button" value="회원 상태 변경">
	<table>
		<thead>
			<tr>
				<th><input type="checkbox" onclick="allChk(this.checked)"></th>
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
					<td><input type="checkbox" name="chkId" value="${vo.users_id}"> </td>
					<td>${vs.count}</td>
					<td><a href="./selectUserDetail.do?users_id=${vo.users_id}">${vo.users_id}</a></td>
					<td>${vo.users_name}</td>
					<td>${vo.users_tel}</td>
					<c:if test="${vo.users_status eq 'N'}">
						<td>활동중</td>
					</c:if>
					<c:if test="${vo.users_status eq 'Y'}">
						<td>탈퇴</td>
					</c:if>
					<c:if test="${vo.users_status eq 'S'}">
						<td>활동중지</td>
					</c:if>
					<c:if test="${vo.users_auth eq 'U'}">
					<td>일반사용자</td>
					</c:if>
					<c:if test="${vo.users_auth eq 'H'}">
					<td>병원관계자</td>
					</c:if>
					<c:if test="${vo.users_auth eq 'A'}">
					<td>관리자</td>
					</c:if>
					<c:if test="${vo.users_crn > 0}">
					<td>${vo.users_crn}</td>
					</c:if>
					<c:if test="${vo.users_crn == 0}">
					<td></td>
					</c:if>
					<td>${vo.users_joindate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</form>
</div>
</body>
<%@ include file="./footer.jsp" %>
</html>