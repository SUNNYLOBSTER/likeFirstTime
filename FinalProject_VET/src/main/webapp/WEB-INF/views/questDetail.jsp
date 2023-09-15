<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세조회</title>
</head>
<body>
${lists[0]}
<table>
	<thead>
		<tr>
<!-- 			<th style="width:100px;content-align:center;">작성자</th> -->
<!-- 			<th style="width:200px;content-align:center;">제목</th> -->
<!-- 			<th style="width:500px;content-align:center;">내용</th> -->
<!-- 			<th>작성일</th> -->
		</tr>
	</thead>
	<tbody>
<!-- 		<tr> -->
<%-- <%-- 			<td>${lists.users_name}</td> --%>
<%-- 			<td>${lists.qst_title}</td> --%>
<%-- 			<td><a>${lists.qst_content}</a></td> --%>
<!-- 			<td> -->
<%-- 				<fmt:parseDate var="questDate" value="${lists.qst_regdate}" pattern="yyyy-MM-dd HH:mm"/> --%>
<%-- 				<fmt:formatDate value="${questDate}" pattern="yyyy-MM-dd HH:mm"/> --%>
<!-- 			</td> -->
<!-- 		</tr> -->
	</tbody>
</table>
<table>
	<thead>
<!-- 		<tr> -->
<!-- 			<th style="width:100px;content-align:center;">작성자</th> -->
<!-- 			<th style="width:500px;content-align:center;">내용</th> -->
<!-- 			<th>작성일</th> -->
<!-- 		</tr> -->
	</thead>
	<tbody>
<!-- 		<tr> -->
<%-- 			<c:forEach var="dto" items="${qstVo}" varStatus="vs"> --%>
<%-- 			<td>${dto.rpy_id}</td> --%>
<%-- 			<td><a>${dto.rpy_content}</a></td> --%>
<!-- 			<td> -->
<%-- 				<fmt:parseDate var="replyDate" value="${dto.rpy_regdate}" pattern="yyyy-MM-dd HH:mm"/> --%>
<%-- 				<fmt:formatDate value="${replyDate}" pattern="yyyy-MM-dd HH:mm"/> --%>
<!-- 			</td> -->
<%-- 			</c:forEach> --%>
<!-- 		</tr> -->
	</tbody>
</table>
</body>
</html>