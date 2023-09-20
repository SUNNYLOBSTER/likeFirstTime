$(document).ready(function() {
	$(function(){
		var request = $.ajax({
			url:"./fullCalendar_sche.do",
			method: "get",
			dataType:"json"
		});
		
		request.done(function(data){
			console.log(data);
			var today = new Date();
			var calendarEl = document.getElementById('calendar');
			
			var calendar = new FullCalendar.Calendar(calendarEl,{
				initialDate: today,
				editable: false, 
				selectable: true, 
				dayMaxEvents: true,
				navLinks: true, 
				locale: "ko",
				headerToolbar: {
					left: 'dayGridMonth,addEventButton',
					center: 'title',
					right: 'prev,next today'
				},
				customButtons: {
					addEventButton:{
						text: "일정 추가",
						click : function(){
							console.log("일정추가 실행");
							$("#calendarModal").modal("show");
							
							$("#addCalendar").on("click",function(){
								var title = $("#sche_title").val();
								var date = $("#sche_date").val();
								var content = $("#sche_content").val();
								var timeArray = $("#sche_time").val().split(":");
								var hour = timeArray[0];
								var minute = timeArray[1];
								var obj = {
										"sche_title" : title,
										"sche_date" : date,
										"sche_content" : content,
										"sche_hour" : hour,
										"sche_minute" : minute
									}
								console.log(obj);
								
							});
							
							$("#addCalendar").on("click",function(){
								var title = $("#sche_title").val();
								var date = $("#sche_date").val();
								var content = $("#sche_content").val();
								var time = $("#sche_time").val();
								var timeArray = time.split(":");
								var hour = timeArray[0];
								var minute = timeArray[1];
								
								if(!title){
									alert("일정을 입력해주세요")
									$("#sche_title").focus();
									return false;
								}else if(!date){
									alert("날짜를 입력해주세요")
									$("#sche_date").focus();
									return false;
								}
								
								
								console.log("추가버튼 실행");
								$.ajax({
									url:"./insertNewSchedule.do",
									method: "post",
									data:{
										sche_title : title,
										sche_date : date,
										sche_content : content,
										sche_hour : hour,
										sche_minute : minute
									},
									success:function(){
										console.log("일정등록 성공");
										alert("일정이 등록되었습니다");
										location.href='./selectAllSchedule.do';
									},
									error:function(){
										alert("일정등록이 실패했습니다");
										location.href='./selectAllSchedule.do';
										console.log("일정등록 실패");
									}
									
								});
							});
						}
					} 
				},
				eventClick: function(info) {
					$("#detailModal").modal("show");
					$.ajax({
						url:"./selectOneSchedule.do",
						method:"get",
						data:{sche_num: info.event._def.extendedProps.sche_num},
						success:function(data){
							console.log(data);
						},
						error:function(){
							
						}
					});
					
//				    location.href="./selectOneSchedule.do?sche_num="+info.event._def.extendedProps.sche_num;
				},
				events: data
				
			});
			calendar.render();
		});
		request.fail(function(textStatus) {
			alert("요청 실패 : " + textStatus);
			location.href="./loginForm.do";
		});
	});
});