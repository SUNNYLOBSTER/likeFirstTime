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
				 	html += "<option value='"+data.list[i].medi_code+"' id='medi_s'>"+data.list[i].medi_name+"</option>";
					obj.append(html);
				}
				obj.empty().append(html);
			},
			error:function(){
				console.log("값 전달 오류");
			}
		});
		
	}