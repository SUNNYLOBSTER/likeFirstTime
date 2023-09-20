<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>진료문의 게시판</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link href="stylesheet" href="./css/questBoard.css">

</head>
<body>
<!-- 카테고리 선택 및 검색창 -->
<div class="container">

<div id="questSearch">
	<select id="aCode" >
		<option disabled="disabled" selected="selected" value="">--어떤 동물인가요?--</option>
		<option value="d">개</option>
		<option value="c">고양이</option>
		<option value="r">파충류</option>
		<option value="b">조류</option>
		<option value="f">어류</option>
		<option value="o">기타</option>
	</select>

	<select id="aPart">
		<option disabled="disabled" selected="selected" value="">---</option>
		<option value="01">피부</option>
		<option value="02">눈</option>
		<option value="03">치아</option>
		<option value="04">위,장</option>
		<option value="05">신장,방광</option>
		<option value="06">뼈,관절</option>
		<option value="07">심장</option>
		<option value="08">간</option>
		<option value="09">면역력,호흡기</option>
		<option value="10">기타</option>
	</select>
	<input id="searchBar" type="text" placeholder="검색어를 입력하세요" style="text-align: left;">
	<input id="searchSubmit" type="submit" value="검색">
</div>



<div>
<table>
	<thead>
		<tr>
			<th style="width:30px;content-align:center;">번호</th>
			<th style="width:100px;content-align:center;">작성자</th>
			<th style="width:100px;content-align:center;">동물</th>
			<th style="width:200px;content-align:center;">제목</th>
			<th style="width:500px;content-align:center;">내용</th>
			<th>작성일</th>
		</tr>
	</thead>
	<tbody>
		
		<c:forEach var="dto" items="${qstVo}" varStatus="vs">
<%-- 		${dto} --%>
		<tr>
			<td style="text-align:center;">${vs.count}</td>
			<td>${dto.qst_id}</td>
			<td>${dto.qst_species}</td>
			<td>${dto.qst_title}</td>
			<td><a href="./questDetail.do?seq=${dto.qst_seq}">${dto.qst_content}</a></td>
			<td>
				<fmt:parseDate var="questDate" value="${dto.qst_regdate}" pattern="yyyy-MM-dd HH:mm"/>
				<fmt:formatDate value="${questDate}" pattern="yyyy-MM-dd HH:mm"/>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
</div>
<a href="./writeQuest.do">새 글 작성</a><br>


<!-- 페이징 -->
<div class="paging">
</div>

</div>

<script type="text/javascript">


searchSubmit.addEventListener("click", e => {
	var code = aCode.value;
	var text = searchBar.value;
	var part = aPart.value;
	console.log(code, text);
	
	$.ajax({
		url:"./selectPartQuest.do",
		method:"get",
		data:"qst_species="+code + "&qst_part=" + part + "&qst_content=" + text,
		success:function(msg){
			console.log("성공");
		},
		error:function(){
			alert("잘못된 요청");
		}
	});
	
})






</script>
</body>
</html>