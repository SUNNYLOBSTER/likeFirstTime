//window.onload = function(){
//var modal = document.getElementById("modalWindow");
//var btn = document.getElementById("openModal");
//var span = document.getElementsByClassName("close")[0];
//var close = document.getElementById("closeModal");
//
//btn.onclick = function() {
// var btnVal =btn.value;
// console.log("val",btnVal)
//  modal.style.display = "block";
//   modal_submit.onclick =function(btnVal){
//	console.log(btnVal);
//}
//}
//
//span.onclick = function() {
//  modal.style.display = "none";
//}

//close.onclick = function() {
//  modal.style.display = "none";
//}
//window.onclick = function(event) {
//  if (event.target == modal) {
//    modal.style.display = "none";
//  }
//}

//};

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
//	location.href="./chooseReply.do?seq="+result_btn;
	$.ajax({
		url:"./chooseReply.do",
		method:"get",
		data:"seq="+result_btn,
		success:function(msg){
			modal.style.display="none";
		},
		error:function(){
			alert("문제많아요");
			modal.style.display="none";
		}
	});
}

//};