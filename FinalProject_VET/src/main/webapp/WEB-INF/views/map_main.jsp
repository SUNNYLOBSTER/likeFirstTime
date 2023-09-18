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
		<div>동물병원 찾기</div>
		<button onclick="">내 주변 동물병원</button>
		<div id="map" style="width: 1000px; height: 700px;"></div>
	</div>

</body>
<script type="text/javascript" src="./js/map_main.js"></script>
<%@ include file="./footer.jsp"%>
</html>