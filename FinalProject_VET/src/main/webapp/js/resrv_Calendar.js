document.addEventListener('DOMContentLoaded', function() {
	$(function() {
		var request = $.ajax({
			url: "./fullCalendar.do",
			method: "GET",
			dataType: "json"
		});

		request.done(function(data) {
			console.log(data); // 가져온 일정
			var today = new Date(); // 오늘 날짜 가져오기
			var calendarEl = document.getElementById('calendar'); //캘린더 뿌려질 위치
			
			var calendar = new FullCalendar.Calendar(calendarEl, {
				initialDate: today,
				editable: false, // 일정 수정 기능 못쓰게
				selectable: true, //일자 선택 가능
				businessHours: true, //업체 휴무일 가져오기 true
				dayMaxEvents: true, //이벤트 너무 많으면 '더 보기' 형태로 출력되게
				locale: "ko",
				headerToolbar: {
					left: '',
					center: 'title',
					right: 'prev,next today'
				},
				events: data
			});
			calendar.render();
		});
		request.fail(function(textStatus) {
			alert("요청 실패 : " + textStatus);
		});
	});

//	var calendarEl = document.getElementById('calendar');
//	var today = new Date();
//	var calendar = new FullCalendar.Calendar(calendarEl, {
//      headerToolbar: {
//        left: 'prev,next today',
//        center: 'title',
//        right: 'dayGridMonth,timeGridWeek,timeGridDay'
//      },
//      initialDate: today, // 초기 로딩 날짜.
//      navLinks: true,  // 일정 수정 기능 못쓰게
//      selectable: true, //일자 선택 가능
//      selectMirror: true, //업체 휴무일 가져오기 true
//      dayMaxEvents: true, //이벤트 너무 많으면 '더 보기' 형태로 출력되게
//    
});
