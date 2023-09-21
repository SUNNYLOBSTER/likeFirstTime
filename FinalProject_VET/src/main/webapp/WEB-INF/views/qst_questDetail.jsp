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
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="./js/questBoard.js"></script>
<link rel="stylesheet" href="./css/questBoard.css">
<%@ include file="./header.jsp" %>
</head>
<body>
<%-- ${qstDetail} --%>
<!-- <hr> -->
<%-- ${rpyList} --%>
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
	<table style="border-style:solid;">
		<thead>
			<tr>
				<th>작성자</th>
				<th style="width:600px;">내용</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="loop_flag" value="false" />
			<c:forEach var="reply" items="${rpyList}">
			<c:choose>
				<c:when test="${reply.rpy_chosen eq 'Y'}">
				<c:set var="loop_flag" value="true" />
			    	<tr style="border-color:red; border-style: solid;" onclick="location.href='./'">
				    	<td>${reply.hospital_vo[0].hosp_name}</td>
					    <td style="width:600px;">${reply.rpy_content}</td>
						<td>
						    <fmt:parseDate var="replyDate" value="${reply.rpy_regdate}" pattern="yyyy-MM-dd HH:mm"/>
						    <fmt:formatDate value="${replyDate}" pattern="yyyy-MM-dd HH:mm"/>
						    <button id="openModal" type="button" style="display:none;" class="btn btn-primary" onclick="selected('${reply.rpy_seq}')" value="${reply.rpy_seq}">채택하기</button>
					    </td>
				    </tr>
			    </c:when>
			    <c:otherwise>
					<tr>
				    	<td><a href="./selectUserDetail.do?id=${reply.rpy_id}">${reply.hospital_vo[0].hosp_name}</a></td>
					    <td style="width:600px;">${reply.rpy_content}</td>
						<td>
						    <fmt:parseDate var="replyDate" value="${reply.rpy_regdate}" pattern="yyyy-MM-dd HH:mm"/>
						    <fmt:formatDate value="${replyDate}" pattern="yyyy-MM-dd HH:mm"/>
							<c:if test="${not loop_flag }">
								<button id="openModal" type="button" class="btn btn-primary" onclick="selected('${reply.rpy_seq}')" value="${reply.rpy_seq}">채택하기</button>
							</c:if>
					    </td>
					</tr>
					</c:otherwise>
			</c:choose>
			</c:forEach>
		</tbody>	
	</table>
</div>
<input type="submit" value="돌아가기" onclick="location.href='./questBoard.do'">

<div id="modalWindow" class="modal">
    <div class="modal-content">
        <h3>채택하시겠습니까?</h3><span class="close">&times;</span>
        <p style="font-size: 8px; color: grey;">※ 채택은 취소할 수 없습니다.</p>
    <button id="choiceModal" onclick="result()">네!</button>
    <button id="closeModal">아니오</button>
    </div>
</div>

<script>
  <c:if test="${hideOpenModal == 'true'}">
    $(document).ready(function() {
      $('#openModal').hide();
    });
  </c:if>
</script>

</div>
</body>
<%@ include file="./footer.jsp" %>
</html>