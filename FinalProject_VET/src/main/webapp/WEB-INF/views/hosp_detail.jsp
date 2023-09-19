<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/hosp_detail.css">
<title>병원 상세페이지</title>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=238e7e3447ac847cdba75075c7d23f2e&libraries=services,clusterer,drawing"></script>
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
			<div id="map"></div>
		</div>
	</div>
	<div class="insertDetail">
		<h2>동물병원 상세정보</h2>
		<table>
			<tr>
				<th>병원이름</th>
				<td id="hosp_name">${hosp_detail.hospital_vo[0].hosp_name}</td>
			</tr>
			<tr>
				<th>병원주소</th>
				<td id="hosp_addr">${hosp_detail.users_addr}</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>${hosp_detail.users_tel}</td>
			</tr>
			<tr>
				<th>운영시간</th>
				<td>${hosp_detail.hospital_vo[0].hosp_time}</td>
			</tr>
			<tr>
				<th>휴무일</th>
				<td>${hosp_detail.hospital_vo[0].hosp_off}</td>
			</tr>
			<tr>
				<th>주차</th>
				<c:choose>
					<c:when test="${hosp_detail.hospital_vo[0].hosp_park eq 'Y'}">
						<td>가능</td>
					</c:when>
					<c:otherwise>
						<td>불가</td>
					</c:otherwise>
				</c:choose>
			</tr>
			<tr>
				<th>병원 소개</th>
				<td>${hosp_detail.hospital_vo[0].hosp_etc}</td>
			</tr>
		</table>
	</div>
</div>
<script type="text/javascript" src="./js/map_hospDetail.js"></script>
</body>
<%@ include file="./footer.jsp" %>
</html>