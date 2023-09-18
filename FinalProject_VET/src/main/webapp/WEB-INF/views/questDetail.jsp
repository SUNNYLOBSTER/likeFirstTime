<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="./js/questBoard.js" ></script>
<title>게시글 상세조회</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link href="stylesheet" href="./css/questBoard.css">

</head>
<body>
<%-- ${qstDetail} --%>
<!-- <hr> -->
<%-- ${rpyList[0]} --%>
<div id="container">

<table>
	<thead>
		<tr>
			<th style="width:150px;content-align:center;">작성자</th>
			<th style="width:200px;content-align:center;">제목</th>
			<th style="width:500px;content-align:center;">내용</th>
			<th style="width:200px;content-align:center;">작성일</th>
		</tr>
	</thead>
	<tbody>
		<tr>
	 		<td>${qstDetail[0].users_vo[0].users_name}</td>
			<td>${qstDetail[0].qst_title}</td>
			<td>${qstDetail[0].qst_content}</td>
 			<td> 
				<fmt:parseDate var="questDate" value="${qstDetail[0].qst_regdate}" pattern="yyyy-MM-dd HH:mm"/>
				<fmt:formatDate value="${questDate}" pattern="yyyy-MM-dd HH:mm"/>
 			</td> 
 		</tr> 
	</tbody>
</table>
<hr>

<div>
	<table>
		<thead>
			<tr>
				<th>작성자</th>
				<th style="width:600px;">내용</th>
				<th>작성일</th>
			</tr>
		</thead>
		
		<tbody>
				<c:forEach var="reply" items="${rpyList}">
			    <tr>
			    	<td>${reply.hospital_vo[0].hosp_name}</td>
				    <td style="width:600px;">${reply.rpy_content}</td>
					<td>
				    <fmt:parseDate var="replyDate" value="${reply.rpy_regdate}" pattern="yyyy-MM-dd HH:mm"/>
				    <fmt:formatDate value="${replyDate}" pattern="yyyy-MM-dd HH:mm"/>
				    </td>
				    <td>
				    <input id="btn-modal" type="button" value="채택하기" onclick="href='./choosePage.do'"/>
				    </td>
			    </tr>
				</c:forEach>
		</tbody>
	</table>
</div>
<input type="submit" value="돌아가기" onclick="location.href='./questBoard.do'">


    <div id="modal" class="modal-overlay">
        <div class="modal-window">
            <div class="title">
                <h2>답변채택</h2>
            </div>
            <div class="close-area">X</div>
            <div class="content">
                <p style="font-size: 16px;">채택하시겠습니까?</p>
                <p style="font-size: 9px; color: light-grey;">채택은 취소할 수 없습니다.</p>
            </div>
        </div>
    </div>


</div>
</body>
</html>