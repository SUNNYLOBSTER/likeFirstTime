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
				<li class="li_btn"><button id="map_btn2" onclick="showSelect()">지역별 동물병원 찾기</button></li>
				<li class="li_btn"><button id="map_btn1" onclick="panTo()">내 주변 동물병원</button></li>
			</ul>
			<ul id="region_on">
				<li>
					<button onclick="regionSearch()">조회</button>
				</li>
				<li class="li_select">
					<select id="option_sigungu">
						<option value="1">--시/군/구--</option>
					</select>
				</li>
				<li class="li_select">
					<select id="option_sido" onchange="sidoChange()">
						<option>--시/도--</option>
						<c:forEach var="sido" items="${sidoList}">
							<option class="sido" value="${sido.si_do}">${sido.si_do}</option>
						</c:forEach>
					</select>
				</li>
			</ul>
			<div id="map"></div>
			<div id="regionMap"></div>
		</div>
	</div>

</body>
<script type="text/javascript" src="./js/map_main.js"></script>
<script type="text/javascript" src="./js/map_regionMap.js"></script>
<%@ include file="./footer.jsp"%>
</html>