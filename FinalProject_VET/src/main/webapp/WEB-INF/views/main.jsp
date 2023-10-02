<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
</head>
<link rel="stylesheet" href="./css/main_Page.css">
<%@ include file="./header.jsp" %>
<body>
<div id="mainContainer">
		<div id="map_info">
			<img id="mapImage" alt="지도" src="./img/map-image.jpg">
			<div id="map_descript">
				<h2>동물병원 찾기 및 진료예약 서비스</h2>
				<p>내 주변 동물병원, 지역별 동물병원 찾기 서비스를 통해 진료예약 서비스를 이용해보세요!</p>
			</div>
		</div>
		<div id="question_info">
			<img id="qnaImage" alt="진료문의" src="./img/qnaImage.png">
			<div id="question_descript">
				<h2>진료문의 서비스</h2>
				<p>반려동물이 어디가 아픈지, 어떤 질병인지 궁금하시다면 진료문의 서비스를 이용해보세요!</p>
			</div>
		</div>
		<div id="notice_info">
			<div id="notice_descript">
				<div id="notice">
					<b>공지사항</b>
					<a href="#" id="noti_plus">더보기 +</a>
				</div>
				<table>
					<c:forEach var="noti" items="${notice_list}">
						<tr>
							<td>
								<fmt:parseDate var="notidate" value="${noti.noti_regdate}" pattern="yyyy-MM-dd"/>
								<fmt:formatDate value="${notidate}" pattern="yyyy-MM-dd"/>
							</td>
							<td>${noti.noti_title}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div id="chosen_hosp">
			<div id="chosen_hosp_descript">
				<div id="chosen">
					<b>우수 답변 동물병원</b>
				</div>
				<table>
					<tr>
						<th>순위</th>
						<th>동물병원</th>
						<th>답변</th>
						<th>채택</th>
					</tr>
					<c:forEach var="rank" items="${chsn_list}" varStatus="vs">
							<tr>
								<th>${vs.count}.</th>
								<td>${rank.users_vo.users_name}</td>
								<td>${rank.rpy_cnt}</td>
								<td>${rank.rpy_chosen}</td>
							</tr>
					</c:forEach>
				</table>
			</div>
		</div>
</div>

</body>

<%@ include file="./footer.jsp" %>
</html>