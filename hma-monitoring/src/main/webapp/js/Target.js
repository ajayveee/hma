/**
 * 
 */
$(document).ready(function() {

	$("#sdatepicker").datepicker();
	$("#edatepicker").datepicker();
	$("#usr").text(userDetails.firstName + " " + userDetails.lastName);
	$("#actBtn").prop("disabled", false);
	//fetchUserDetails(getQueryVariable("user"));
});
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
	$("#usr").text(userDetails.firstName + " " + userDetails.lastName);
	// fetchActivities(userDetails);
	 
}
function fetchTargets() {
//	var target = new Object();
//	target.user = {
//		"username" : userDetails.username
//	};
//	target.startDate = $("#sdatepicker").datepicker("getDate");//new Date(2014, 10, 15, 0, 0, 0, 0);
//	target.endDate = $("#edatepicker").datepicker("getDate");//new Date(2014, 10, 25, 0, 0, 0, 0);
	$.ajax({
		type : "GET",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		url : "http://localhost:8080/hma-monitoring/rest/target/"+userDetails.username,
		//data : JSON.stringify(target)
	// success : activitySuccess(data)
	}).done(targetSuccess);
}
var dateOptions = {
		//weekday: "long",  
	    year: "numeric", month: "short",
	    day: "numeric", hour: "2-digit", minute: "2-digit"
	};
function targetSuccess(data) {
	if (data.status == "FAILURE") {
		$("#resp").text("Failure.." + data.responseMsg);
		return;
	}
	$('#targetTable').dataTable().fnDestroy();
	$('#targetTable > tbody').html("");
	for (target in data.targets) {
		$('#targetTable').append(
				"<tr><td>" + data.targets[target].activityType.activity
						+ "</td><td>" + new Date(data.targets[target].startDate).toLocaleTimeString("en-US", dateOptions)
						+ "</td><td>" + new Date(data.targets[target].endDate).toLocaleTimeString("en-US", dateOptions)
						+ "</td><td>"
						+ data.targets[target].completedPercentage
						+ "%</td></tr>");
	}
	$('#targetTable').show().DataTable({
		"autoWidth" : true
	}).columns.adjust().draw();
}
function getQueryVariable(variable) {
	var query = window.location.search.substring(1);
	var vars = query.split('&');
	for (var i = 0; i < vars.length; i++) {
		var pair = vars[i].split('=');
		if (decodeURIComponent(pair[0]) == variable) {
			return decodeURIComponent(pair[1]);
		}
	}
	console.log('Query variable %s not found', variable);
}