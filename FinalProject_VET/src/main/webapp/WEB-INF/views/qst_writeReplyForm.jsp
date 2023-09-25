<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답글 작성화면</title>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<link rel="stylesheet" href="./css/ckeditor.css">
<link rel="stylesheet" href="./css/index.css">
<%@ include file="./header.jsp" %>
</head>
<body data-editor="ClassicEditor" data-collaboration="false" data-revision-history="false">
<div id="container">
	
	<!--  내용 작성 -->
	<textarea id="editor" name="replyContent" required></textarea>
	
</div>
</body>
<script type="text/javascript" src="./lib/ckeditor5-39.0.1/build/ckeditor.js"></script>
<script type="text/javascript" src="./js/ckeditor.js"></script>
<%@ include file="./footer.jsp" %>
<script type="text/javascript" src="./js/qst_writeReply.js"></script>
</html>	