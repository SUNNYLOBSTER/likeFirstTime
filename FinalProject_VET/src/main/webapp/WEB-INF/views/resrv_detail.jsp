<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약신청 페이지</title>
</head>
<body>
	<h2>예약신청 완료</h2>
	<table>
		<tr>
			<th>예약번호 : </th>
			<td>${resrv_detail.resrv_num}</td>
		</tr>
		<tr>
			<th>예약 동물병원 : </th>
			<td>${resrv_detail.resrv_hops}</td>
		</tr>
		<tr>
			<th>예약날짜 : </th>
			<td>${resrv_detail.resrv_visit}</td>
		</tr>
		<tr>
			<th>예약시간 : </th>
			<td>${resrv_detail.resrv_time}시 00분</td>
		</tr>
		<tr>
			<th>예약자명 : </th>
			<td>${resrv_detail.resrv_name}</td>
		</tr>
		<tr>
			<th>예약자 전화번호 : </th>
			<td>${resrv_detail.resrv_tel}</td>
		</tr>
		<tr>
			<th>예약 메모 : </th>
			<td>${resrv_detail.resrv_memo}</td>
		</tr>
	</table>
	<hr>
	<button onclick="location.href='./main.do'">메인홈이동</button>
</body>
</html>