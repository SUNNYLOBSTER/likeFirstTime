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
<link rel="stylesheet" href="./css/qst_questBoard.css">
</head>
<%@ include file="./header.jsp" %>
<body>
<%-- ${qstDetail} --%>
<!-- <hr> -->
<%-- ${rpyList} --%>
<div id="container">

<!-- 게시글 영역 -->
	<div class="card">
		<table class="questTop">
			<tr>
				<td class="questId">${qstDetail[0].users_vo[0].users_name}</td>
				<td class="questCategory">${qstDetail[0].animalcode_vo[0].anm_species}</td>
				<td class="questTitle">${qstDetail[0].qst_title}</td>
				<td class="questDate"> 
					<fmt:parseDate var="questDate" value="${qstDetail[0].qst_regdate}" pattern="yyyy-MM-dd HH:mm"/>
					<fmt:formatDate value="${questDate}" pattern="yyyy-MM-dd HH:mm"/>
				</td> 
			</tr> 
		</table>
		<div >
			<a>${qstDetail[0].qst_content}</a>
		</div>
	</div>
	<input type="submit" value="수정하기" style="display: none;">
<hr>

<!-- 답글영역 -->
<div id="contentArea">
	<c:set var="loop_flag" value="false" />
	<c:forEach var="reply" items="${rpyList}">
	<c:choose>
		<c:when test="${reply.rpy_chosen eq 'Y'}">
		<c:set var="loop_flag" value="true" />
			<div class="card">
				<table class="questTop">
					<tr style="border-color:red; border-style: solid;">
						<td class="replyTitle"><a href="./select_HospDetail.do?hosp_id=${reply.rpy_id}">${reply.hospital_vo[0].hosp_name}</a></td>
						<td class="questDate">
						    <fmt:parseDate var="replyDate" value="${reply.rpy_regdate}" pattern="yyyy-MM-dd HH:mm"/>
						    <fmt:formatDate value="${replyDate}" pattern="yyyy-MM-dd HH:mm"/>
						    <button id="openModal" type="button" style="display:none;" class="btn btn-primary" onclick="selected('${reply.rpy_seq}')" value="${reply.rpy_seq}">채택하기</button>
						</td>
					</tr>
				</table>
				<div class="questBottom">
					<p>${reply.rpy_content}</p>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="card">
				<table class="questTop">
					<tr>
						<td class="replyTitle"><a href="./select_HospDetail.do?hosp_id=${reply.rpy_id}">${reply.hospital_vo[0].hosp_name}</a></td>
						<td class="questDate">
						    <fmt:parseDate var="replyDate" value="${reply.rpy_regdate}" pattern="yyyy-MM-dd HH:mm"/>
						    <fmt:formatDate value="${replyDate}" pattern="yyyy-MM-dd HH:mm"/>
							<c:if test="${not loop_flag }">
								<button id="openModal" type="button" class="btn btn-primary" onclick="selected('${reply.rpy_seq}')" value="${reply.rpy_seq}">채택하기</button>
							</c:if>
					</tr>
				</table>
				<div class="questBottom">
					<p>${reply.rpy_content}</p>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	</c:forEach>
</div>
<input type="submit" value="돌아가기" onclick="location.href='./questBoard.do'">


<div id="modalWindow" class="modal">
    <div class="modal-content">
        <h3>채택하시겠습니까?</h3><span class="close">&times;</span>
        <p style="font-size: 8px; color: grey;">※ 채택은 취소할 수 없습니다.</p>
    <button id="choiceModal" onclick="result()">네</button>
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
<script type="text/javascript" src="./js/qst_questBoard.js"></script>
</html>