
function selected(){
var selected = document.getElementById("openModal").value;
var modal = document.getElementById("modalWindow");
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}
	console.log(selected);
	var result_btn = document.getElementById("choiceModal");
	result_btn.value = selected;
	console.log("결과",result_btn.value);
	var modal = document.getElementById("modalWindow");
	modal.style.display = "block";
	var close = document.getElementById("closeModal");
	close.onclick = function() {
	  modal.style.display = "none";
	}
}

function result(){
	var result_btn = document.getElementById("choiceModal").value;
			var modal = document.getElementById("modalWindow");
	window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}
	console.log(result_btn)
	$.ajax({
		url:"./chooseReply.do",
		method:"get",
		data:"seq="+result_btn,
		success:function(msg){
			modal.style.display="none";
		},
		error:function(){
			alert("잘못된 요청");
			modal.style.display="none";
		}
	});
}

