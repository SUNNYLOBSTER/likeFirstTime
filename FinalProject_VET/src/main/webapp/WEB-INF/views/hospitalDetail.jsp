<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/hospitalDetail.css">
<title>병원 상세페이지</title>
</head>
<%@ include file="./header.jsp" %>
<body>
<div id="container">
	<div class="insertMap">
		<div id="title">
			<div id="bookmarkPart">
				<a href="#">
				<img alt="bookmark" src="./img/bookmark_no.png" id="bookmark">	
				즐겨찾기 등록
				</a>
			</div>
			<span id="hospitalName">병원이름</span>
		</div>
		<div id="map-box">
			<img alt="map" src="./img/map.png" id="mapPic">
		</div>
	</div>
	<div class="insertDetail">
		<span>병원 상세정보</span>
	</div>
</div>
</body>
<%@ include file="./footer.jsp" %>
</html>