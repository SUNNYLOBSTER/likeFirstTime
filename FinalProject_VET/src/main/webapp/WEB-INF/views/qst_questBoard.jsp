<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>진료문의 게시판</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link href="stylesheet" href="./css/questBoard.css">

</head>
<body>
<!-- 카테고리 선택 및 검색창 -->
<div class="container">

<div id="questSearch">qst_questBoard
	<select id="aCode" onchange="aCodeChange()">
		<option>--어떤 동물인가요?--</option>
		<c:forEach var="codeList" items="${codeList}">
			<option value="">
		</c:forEach>
	</select>
	
	<select>

<!-- 			<div id="selectPart"> -->
<!-- 				<select id="codeL" onchange="codeLChange()"> -->
<!-- 					<option>--진료과목--</option> -->
<%-- 					<c:forEach var="codelist" items="${codelists}" varStatus="vs"> --%>
<%-- 						<option value="${codelist.medi_code}">${codelist.medi_name}</option>			 --%>
<%-- 					</c:forEach> --%>
<!-- 				</select> -->
				
<!-- 				<select id="codeS"> -->
<!-- 					<option>--질환--</option> -->
<!-- 				</select> -->
<!-- 				<button id="selectMediCode" onclick="selectCode()">조회</button> -->
<!-- 			</div> -->
<!-- 			<div id="chartPart"> -->

	</select>
	<input type="text" value="❔" style="text-align: left;">
	<input type="submit" value="검색">
</div>

<div>
<table>
	<thead>
		<tr>
			<th style="width:30px;content-align:center;">번호</th>
			<th style="width:100px;content-align:center;">작성자</th>
			<th style="width:100px;content-align:center;">동물</th>
			<th style="width:200px;content-align:center;">제목</th>
			<th style="width:500px;content-align:center;">내용</th>
			<th>작성일</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="dto" items="${qstVo}" varStatus="vs">
		${dto}
		<tr>
			<td style="text-align:center;">${vs.count}</td>
			<td>${dto.qst_id}</td>
			<td>${dto.qst_species}</td>
			<td>${dto.qst_title}</td>
			<td><a href="./questDetail.do?seq=${dto.qst_seq}">${dto.qst_content}</a></td>
			<td>
				<fmt:parseDate var="questDate" value="${dto.qst_regdate}" pattern="yyyy-MM-dd HH:mm"/>
				<fmt:formatDate value="${questDate}" pattern="yyyy-MM-dd HH:mm"/>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
</div>
<a href="./writeQuest.do">새 글 작성</a><br>


<!-- 페이징 -->
<div class="paging">
</div>

</div>
</body>
</html>