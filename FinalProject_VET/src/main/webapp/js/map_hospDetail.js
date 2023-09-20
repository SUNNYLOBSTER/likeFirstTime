var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(37.476513, 126.880105), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

var hosp_addr = document.getElementById("hosp_addr");
var hosp_name = document.getElementById("hosp_name");
var addr = document.getElementById("addr");
var hospitalName = document.getElementById("hospitalName");
//console.log(hosp_addr.innerHTML);
//console.log(hosp_name.innerHTML);
//console.log(addr.innerHTML);
// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();
if(hosp_addr != null){
	// 주소로 좌표를 검색합니다
	geocoder.addressSearch(hosp_addr.innerHTML, function(result, status) {
	
	    // 정상적으로 검색이 완료됐으면 
	     if (status === kakao.maps.services.Status.OK) {
	
	        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	
	        // 결과값으로 받은 위치를 마커로 표시합니다
	        var marker = new kakao.maps.Marker({
	            map: map,
	            position: coords
	        });
	
	        // 인포윈도우로 장소에 대한 설명을 표시합니다
	        var infowindow = new kakao.maps.InfoWindow({
	            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+hosp_name.innerHTML+'</div>'
	        });
	        infowindow.open(map, marker);
	
	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	        map.setCenter(coords);
	    } 
	});    
}else{
	// 주소로 좌표를 검색합니다
	geocoder.addressSearch(addr.innerHTML, function(result, status) {
	
	    // 정상적으로 검색이 완료됐으면 
	     if (status === kakao.maps.services.Status.OK) {
	
	        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	
	        // 결과값으로 받은 위치를 마커로 표시합니다
	        var marker = new kakao.maps.Marker({
	            map: map,
	            position: coords
	        });
	
	        // 인포윈도우로 장소에 대한 설명을 표시합니다
	        var infowindow = new kakao.maps.InfoWindow({
	            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+hospitalName.innerHTML+'</div>'
	        });
	        infowindow.open(map, marker);
	
	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	        map.setCenter(coords);
	    } 
	});    
}

var hosp_time = document.getElementById("hosp_time");
if(hosp_time != null){
//	console.log(typeof(hosp_time.innerHTML));
	hosp_time.style.display = "none";
	var hosp_runTime = JSON.parse(hosp_time.innerHTML);
	console.log(hosp_runTime);
	var hosp_runtime = document.getElementById("hosp_runtime");
	hosp_runtime.innerHTML = hosp_runTime.open +"~"+ hosp_runTime.close + "시";
}
