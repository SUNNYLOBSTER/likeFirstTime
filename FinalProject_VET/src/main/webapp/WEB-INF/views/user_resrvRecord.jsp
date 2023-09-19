<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%@ include file="./header.jsp" %>
<script type="text/javascript" src='./js/resrv_user.js'></script>
<link rel="stylesheet" href="./css/user_resrvRecord.css">
<body>
	<div class="sidenav">
		<button id="sideNav_1" class="dropdown-btn">
			진료기록<i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-container">
		</div>
		<button class="dropdown-btn">
			진료문의 내역<i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-container">
			<a onclick="#">1</a>
		</div>
		<button class="dropdown-btn">
			일정관리<i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-container">
			<a onclick="#">1</a>
		</div>
		<button class="dropdown-btn">
			예약내역<i class="fa fa-caret-down" onclick="#"></i>
		</button>
		<div class="dropdown-container">
		</div>
		<button class="dropdown-btn">
			결제내역<i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-container">
			<a onclick="#">1</a>
		</div>
		<button class="dropdown-btn">
			즐겨찾기<i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-container">
			<a onclick="#">1</a>
		</div>
		<button class="dropdown-btn">
			내 정보관리<i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-container">
			<a onclick="#">1</a>
		</div>
		<button class="dropdown-btn">
			채팅<i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-container">
			<a onclick="#">1</a>
		</div>
	</div>
	<jsp:useBean id="today" class="java.util.Date"/>
	<div class="navContainer">
		<c:forEach var="list" items="${hosp_lits}">
			<table class="resrv_record">
				<tr>
					<th>예약번호</th>
					<td>${list.resrv_num}</td>
					<th>예약병원</th>
					<td>${list.resrv_hops}</td>
				</tr>
				<tr>
					<th>방문일자</th>
					<td>${list.resrv_visit}</td>
					<th>예약시간</th>
					<td>${list.resrv_time}</td>
				</tr>
				<tr>
					<th>예약자명</th>
					<td>${list.resrv_name}</td>
					<th>예약상태</th>
					<c:choose>
						<c:when test="${list.resrv_status eq 'Y'}">
							<th>확정</th>
						</c:when>
						<c:when test="${list.resrv_status eq 'N'}">
							<th>취소</th>
						</c:when>
						<c:otherwise>
							<td>대기중</td>
						</c:otherwise>
					</c:choose>
					
				</tr>
				<tr>
					<th colspan="4">메모</th>
				</tr>
				<tr>
				<c:choose>
					<c:when test="${list.resrv_memo eq null}">
						<th colspan="4">-</th>
					</c:when>
					<c:otherwise>
						<td colspan="4">${list.resrv_memo}</td>
					</c:otherwise>
				</c:choose>
				</tr>
			</table>
		</c:forEach>
	</div>
</body>
<script type="text/javascript">
	var dropdown = document.getElementsByClassName("dropdown-btn");
	var i;
	
	for (i = 0; i < dropdown.length; i++) {
	  dropdown[i].addEventListener("click", function() {
	    this.classList.toggle("active");
	    var dropdownContent = this.nextElementSibling;
	    if (dropdownContent.style.display === "block") {
	      dropdownContent.style.display = "none";
	    } else {
	      dropdownContent.style.display = "block";
	    }
	  });
	}
</script>
<%@ include file="./footer.jsp" %>
</html>