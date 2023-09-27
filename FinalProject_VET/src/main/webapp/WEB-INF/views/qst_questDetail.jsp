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
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<link rel="stylesheet" href="./css/qst_questBoard.css">
<link rel="stylesheet" href="./css/ckeditor.css">
<link rel="stylesheet" href="./css/index.css">
<%@ include file="./header.jsp" %>
</head>
<body data-editor="ClassicEditor" data-collaboration="false" data-revision-history="false">
<body>
<%-- ${qstDetail} --%>
<%-- ${rpyList} --%>
<%-- ${content} --%>
<%-- ${qstDetail[0].qst_id} --%>
<hr>
<%-- ${loginVo.users_id} --%>
<div id="container">
<!-- 게시글 영역 -->
	<div class="questCard">
		<table class="questTop">
			<tr>
				<td class="questId">${qstDetail[0].users_vo[0].users_name}</td>
				<td class="questCategory">${qstDetail[0].animalcode_vo[0].anm_species}</td>
				<td class="questPart">${qstDetail[0].animalpart_vo[0].part_name}</td>
				<td class="questTitle">${qstDetail[0].qst_title}</td>
				<td class="questDate"> 
					<fmt:parseDate var="questDate" value="${qstDetail[0].qst_regdate}" pattern="yyyy-MM-dd HH:mm"/>
					<fmt:formatDate value="${questDate}" pattern="yyyy-MM-dd HH:mm"/>
				</td> 
			</tr> 
		</table>
		<div >
			<a>${content}</a>
		</div>
	</div>
<hr>
	<div id="gapArea">
	<c:if test="${loginVo.users_id eq qstDetail[0].qst_id}">
<%-- 		<c:if test="${loginVo.users_id eq qstDetail[0].user_vo[0].users_id}"> --%>
			<input type="submit" style="float:right;" value="수정"><a onclick="location.href=.//qst_writeQuest.do"></a></input>
	</c:if>
		<c:if test="${loginVo.users_auth eq 'H'}">
			<input type="submit" style="float:right;" value="답글 작성" onclick="location.href='./writeReplyform.do'">
		</c:if>
	</div>
<hr>
<!-- 답글영역 -->
<div id="contentArea">
	<c:set var="loop_flag" value="false" />
	<c:forEach var="reply" items="${rpyList}">
	<c:choose>
		<c:when test="${reply.rpy_chosen eq 'Y'}">
		<c:set var="loop_flag" value="true" />
			<div class="card-chosen">
				<table class="questTop">
					<tr>
						<td class="replyTitle"><a href="./select_HospDetail.do?hosp_id=${reply.rpy_id}">${reply.hospital_vo[0].hosp_name}</a></td>
						<td class="questDate">
						    <fmt:parseDate var="replyDate" value="${reply.rpy_regdate}" pattern="yyyy-MM-dd HH:mm"/>
						    <fmt:formatDate value="${replyDate}" pattern="yyyy-MM-dd HH:mm"/>
						    <button id="openModal" type="button" style="display:none;" class="btn" onclick="selected('${reply.rpy_seq}')" value="${reply.rpy_seq}">채택하기</button>
							<div>
								<button style="float:right;"><a>신고</a></button>
							</div>
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
						<div>
							<c:if test="${not loop_flag }">
								<c:if test="${loginVo.users_id eq qstDetail[0].qst_id}">
									<button id="openModal" type="button" class="choiceBtn" onclick="selected('${reply.rpy_seq}')" value="${reply.rpy_seq}">채택하기</button>
								</c:if>
							</c:if>
							<button style="margin-left:15px; float:right;"><a>신고</a></button>
						</div>
					</tr>
				</table>
				<div class="questBottom">
					<a>${reply.rpy_content}</a>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	</c:forEach>
</div>

<!-- 답변작성  -->
<div style="display: none;">
	<textarea id="editor" name="questContent" required></textarea>
</div>

<!-- 답변채택 모달 -->
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
<script type="text/javascript" src="./lib/ckeditor5-39.0.1/build/ckeditor.js"></script>
<script type="text/javascript" src="./js/ckeditor.js"></script>
<%@ include file="./footer.jsp" %>
<script type="text/javascript" src="./js/qst_questBoard.js"></script>
</html>