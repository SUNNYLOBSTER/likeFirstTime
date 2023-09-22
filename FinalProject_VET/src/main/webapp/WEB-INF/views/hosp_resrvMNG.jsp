<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>병원 예약관리 페이지</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src='./js/index.global.js'></script> <!-- 캘린더를 랜더링하는 js -->
<script type="text/javascript" src='./js/resrv_Calendar.js'></script> <!-- 작성할 js -->

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="./css/calendar.css">
</head>
<%@ include file="./header.jsp" %>
<script type="text/javascript" src='./js/resrv_SideNav.js'></script>
<body>

		<div class="sidenav">
			<button id="sideNav_1" class="dropdown-btn">
				병원정보<i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-container">
				<a onclick="#">1</a>
			</div>
			<button class="dropdown-btn">
				예약관리<i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-container">
				<a onclick="resrv_calendar()">예약현황</a>
				<a onclick="month_count()">월별 예약건수</a>
				<a onclick="resrv_wList()">예약승인 대기명단</a>
			</div>
			<button class="dropdown-btn">
				진료문의 답글<i class="fa fa-caret-down"></i>
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
	<div class="navContainer">
		<a href="./main.do">메인화면</a>
		<h1>${loginVo.users_name}</h1>
		<div id="calendar"></div>
		<div id="month_cnt"></div>
		<div id="waitList"></div>
		
		<div class="modal fade" id="resrv_detailModal" role="dialog" style="display: none;">
	        <div class="modal-dialog">
	            <div class="modal-content">
	                <div class="modal-header">
	                    <h5 class="modal-title" id="exampleModalLabel">예약 상세조회</h5>
	                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
	                </div>
	                <div class="modal-body">
	                    <div class="form-group">
	                        <label>예약번호</label>
	                        <input type="text" class="form-control" id="resrv_num" name="resrv_num">
	                        <label>예약날짜</label>
	                        <input type="date" class="form-control" id="resrv_visit" name="resrv_visit">
	                        <label>예약시간</label>
	                        <input type="text" class="form-control" id="resrv_time" name="resrv_time">
	                        <label>예약자명</label>
	                        <input type="text" class="form-control" id="resrv_name" name="resrv_name">
	                        <label>예약자 전화번호</label>
	                        <input type="text" class="form-control" id="resrv_tel" name="resrv_tel">
	                        <label>예약 메모</label>
	                        <input type="text" class="form-control" id="resrv_memo" name="resrv_memo">
	                        <input type="hidden" class="form-control" id="resrv_hosp" name="resrv_hosp">
	                    </div>
	                </div>
	                <div class="modal-footer">
	                    <button type="button" id="resrv_modifyBtn" onclick="resrv_modify()">정보수정</button>
	                    <button type="button" id="resrv_saveBtn" onclick="resrv_save()">저장</button>
	                </div>
	            </div>
	        </div>
	    </div>
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