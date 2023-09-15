document.addEventListener('DOMContentLoaded', function() {
	$(function() {
		var request = $.ajax({
			url: "./resrv_requestAjax.do",
			method: "get",
			dataType: "json"
		});

		request.done(function(data) {
			console.log(data); // 가져온 일정
			var today = new Date(); // 오늘 날짜 가져오기
			var oneDayBefore = new Date(today);
			oneDayBefore.setDate(today.getDate()-1);
			console.log(today, oneDayBefore);
			
			var calendarEl = document.getElementById('calendar'); //캘린더 뿌려질 위치
			
		var calendar = new FullCalendar.Calendar(calendarEl, {
				initialDate: today,
				editable: false, // 일정 수정 기능 못쓰게
				selectable: true, //일자 선택 가능
				businessHours: true, //업체 휴무일 가져오기 true
				locale: "ko",
				dayCellDidMount: function(info) {
					if (info.date < oneDayBefore) {
						var bgColor = 'rgba(153, 153, 153, 0.3)';
						info.el.style.backgroundColor = bgColor;
					}
				},
				dateClick: function(info) {
					selectedDateElement.textContent = info.dateStr;
					selectedDateElement.style.color = 'black';

				},
				headerToolbar: {
					left: 'dayGridMonth',
					center: 'title',
					right: 'prev,next today'
				},
				
			});
			
			calendar.render();
			
		});
		
		request.fail(function() {
			alert("가져올 정보가 없습니다. 다시 로그인해주세요");
			location.href="./loginForm.do";
		});
	});

});
