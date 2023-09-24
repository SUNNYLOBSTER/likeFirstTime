<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
</head>
<%@ include file="./header.jsp" %>
<body>
<div id="container">
<%-- ${noticeLists} --%>
<%-- ${loginVo} --%>
	<div>
		<table>
			<thead>
				<tr>
				<c:if test="${loginVo.users_auth eq 'A'}">
				<th>글 수정 버튼</th>
				</c:if>
				<th>번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>작성일자</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="list" items="${noticeLists}" varStatus="vs">
					<tr>
						<td>${vs.count}</td>
						<td><a href="./selectNoticeDetail.do?noti_title=${list.noti_title}">${list.noti_title}</a></td>
						<td>관리자</td>
						<td>${list.noti_regdate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>





</div>
</body>
<%@ include file="./footer.jsp" %>
</html>