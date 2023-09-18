<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<%@ include file="./header.jsp" %>
<script type="text/javascript" src='./js/resrv_user.js'></script>
<style type="text/css">
	.resrv_detail{
		border: 2px solid #ccc;
	}
	#resrv_record table{
		margin-bottom: 10px;
	}
</style>
<body>
	<div class="sidenav">
		<button id="sideNav_1" class="dropdown-btn">
			진료기록<i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-container">
			<a onclick="#">1</a>
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
			예약내역<i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-container">
			<a onclick="resrv('${loginVo.users_id}')">예약신청 내역</a>
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
	<div class="navContainer">
		<h2>${loginVo.users_name}</h2>
		<div id="resrv">
			<h3>예약신청 내역</h3>
			<div id="resrv_record"></div>
		</div>
		<div id="month_cnt"></div>
		<div id="waitList"></div>
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