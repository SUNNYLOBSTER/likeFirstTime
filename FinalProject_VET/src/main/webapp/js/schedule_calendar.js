document.addEventListener('DOMContentLoaded',function(){
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
					left: 'dayGridMonth,dayGridWeek,listWeek',
					center: 'title',
					right: 'prev,next today'
				},
				eventClick: function(info) {
				    console.log("일정 클릭이벤트 :",info.event.title);
				    console.log("일정 클릭이벤트 :",info.event.start);
				    console.log("일정 클릭이벤트 :",info.event._def.extendedProps.resrv_num);
				    location.href="./resrv_detail.do?resrv_num="+info.event._def.extendedProps.resrv_num;
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