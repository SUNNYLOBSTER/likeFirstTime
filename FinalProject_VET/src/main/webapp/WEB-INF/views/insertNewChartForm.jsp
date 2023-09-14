<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 진료기록 작성</title>
<link rel="stylesheet" href="./css/ckeditor.css">
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
</head>
<body>
	<div id="container">
		<h1>새 진료기록 작성</h1>
		<form action="./insertNewChart.do" method="post">
			진료날짜 : <input type="date" id="medi_visit" name="medi_visit"><br>
			진료과목 : <select id="codeL" onchange="codeLChange()">
				<option>--진료 과목--</option>
				<option value="00">일반진료</option>
				<option value="01">내과</option>
				<option value="02">외과</option>
			</select>
			<select id="codeS">
				<option>--질환--</option>
			</select>
			<textarea id="editor" name="medi_content"></textarea>
		</form>
	</div>
	
<script type="text/javascript" src="./lib/ckeditor5-39.0.1/build/ckeditor.js"></script>
<script type="text/javascript" src="./js/ckeditor.js"></script> 
<script type="text/javascript" src="./js/index.js"></script> 
<script type="text/javascript">
	document.getElementById('medi_visit').value = new Date().toISOString().substring(0, 10);
	
	function codeLChange(){
		console.log("codeLChange 실행");
		var codeL = document.getElementById("codeL");
		var selectedValue = codeL.options[codeL.selectedIndex].value;
		console.log("선택된 대분류 : ", selectedValue);
		
		$.ajax({
			url:'./listByCodeS.do',
			method:'post',
			data: "medicodeL="+selectedValue,
			success:function(data){
				console.log(data.list[0].medi_name);
				var obj = $("#codeS");
				var html = "";
				for(let i=0; i<data.list.length ;i++ ){
				 	html += "<option value='"+data.list[i].medi_code+"'>"+data.list[i].medi_name+"</option>";
					obj.append(html);
				}
				obj.empty().append(html);
			},
			error:function(error){
				console.log("값 전달 오류");
			}
		});
		
	}
</script>
</body>
</html>