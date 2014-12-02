/**
 * 
 */
function fetchUserDetails(username) {
	$.ajax({
		type : "GET",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		url : "http://localhost:8080/hma-monitoring/rest/login/" + username
	// data : username
	// success : activitySuccess(data)
	}).done(userSuccess);
}
function userSuccess(data) {
	userDetails = data.user;
	if(userDetails.password != $("#pwdTxt").val()){
		$("#loginResp").html("Invalid Credentials!!");
		return;
	}
	$.removeCookie("userDetails");
	$.cookie("userDetails", userDetails);
	//Set cookie and redirect to activity
	window.location.replace("Activity.html");
}
$(document).ready(function() {

	$("#loginBtn").click(loginBtnClick);
});
function loginBtnClick(){
	fetchUserDetails($("#usrTxt").val());
}