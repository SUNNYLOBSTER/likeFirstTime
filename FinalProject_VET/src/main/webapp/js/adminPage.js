function selectAuth(){
	var userAuth = document.getElementsByName("userAuth")[0];
	var userAuthIndex = userAuth.selectedIndex;
	
	var choiceAuth = userAuth.options[userAuthIndex];
	console.log(choiceAuth.value);
	console.log(choiceAuth.textContent);
	
}

function selectStatus(){
	var userStatus = document.getElementsByName("userStatus")[0];
	var userStatusIndex = userStatus.selectedIndex;
	
	var choiceStatus = userStatus.options[userStatusIndex];
	console.log(choiceStatus.value);
	console.log(choiceStatus.textContent);
	
	
}

function searchUser(){
	var searchUser = document.getElementById("searchUser");
	searchUser.value = searchUser
	
}