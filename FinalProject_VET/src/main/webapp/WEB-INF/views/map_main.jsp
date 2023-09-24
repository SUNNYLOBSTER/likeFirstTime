<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동물병원 찾기</title>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=238e7e3447ac847cdba75075c7d23f2e&libraries=services,clusterer,drawing"></script>
<link rel="stylesheet" href="./css/map_main.css">
</head>
<%@ include file="./header.jsp"%>
<body>

	<div id="container">
		<div id="hosp_mapSearch"><h2>동물병원 찾기</h2></div>
		<div id="map_area">
			<ul id="map_ul">
				<li class="li_btn"><button id="map_btn2">지역별 동물병원 찾기</button></li>
				<li class="li_btn"><button id="map_btn1" onclick="panTo()">현재 위치 돌아오기</button></li>
			</ul>
			<div id="map"></div>
		</div>
	</div>

</body>
<script type="text/javascript" src="./js/map_main.js"></script>
<%@ include file="./footer.jsp"%>
</html>