var hosp_addr = document.getElementById("hosp_addr");
var hosp_name = document.getElementById("hosp_name");
var addr = document.getElementById("addr");
var hospitalName = document.getElementById("hospitalName");
//console.log(hosp_addr.innerHTML);
//--------------------------------------------------------------------------------------------
$(document).ready(function() {
      // 카카오 지도 초기화
      var mapContainer = document.getElementById('map');
      var mapOptions = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 초기 중심 좌표
        level: 2 // 지도 확대 레벨
      };
      var map = new kakao.maps.Map(mapContainer, mapOptions);

      // 주소를 좌표로 변환
      var address = document.getElementById("hosp_addr").innerHTML;
      var geocoder = new kakao.maps.services.Geocoder();
      geocoder.addressSearch(address, function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
          var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

          // 마커 생성
          var marker = new kakao.maps.Marker({
            position: coords,
            map: map
          });

          // 지도 중심을 마커로 이동
          map.setCenter(coords);
        }
      });
    });
//--------------------------------------------------------------------------------------------

//console.log(hosp_name.innerHTML);
//console.log(addr.innerHTML);

var hosp_time = document.getElementById("hosp_time");
if(hosp_time != null){
//	console.log(typeof(hosp_time.innerHTML));
	hosp_time.style.display = "none";
	var hosp_runTime = JSON.parse(hosp_time.innerHTML);
	console.log(hosp_runTime);
	var hosp_runtime = document.getElementById("hosp_runtime");
	hosp_runtime.innerHTML = hosp_runTime.open +"~"+ hosp_runTime.close + "시";
}

function resrv_request(value){
	console.log(value);
	var url = "./resrv_requestPage.do?resrv_hops="+value;
	var title = "예약신청 페이지";
	var prop = "top=100px, left=100px, width=500px, height=600px";
	window.open(url, title, prop);
}

//북마크관련
$(document).on("click","#bookmarkPart",function(){
	console.log("북마크 등록 실행");
		var hosp_id = $("#bookmarkPart").val();
		console.log(hosp_id);
//	$.ajax({
//		
//		url:"./insertNewBookmark.do",
//		method:"post",
//		data: {"bm_hospid":}
//		
//	});
	$('#bookmarkPart').css("background-position", "-286px -410px");
		
});

//function insertBookMark(){
//	console.log("북마크 등록 실행");
//	var bookmarkPart = document.getElementById('bookmarkPart');
//	bookmarkPart.innerHTML = '';
//	var html = '';
//	html+="			<a onclick='insertBookMark()'>";
//	html+="					<img alt='bookmark' src='./img/bookmark_yes.png' id='bookmark'>";
//	html+="			</a>";
//	bookmarkPart.innerHTML = html;
//}