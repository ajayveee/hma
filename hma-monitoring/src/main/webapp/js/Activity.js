/**
 * 
 */
var userDetails;
$(document).ready(function() {

	$("#usr").text(getQueryVariable("user"));
	fetchActivities(getQueryVariable("user"));
});
function fetchUserDetails(username){
	$.ajax({
		type : "POST",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		url : "http://localhost:8080/hma-monitoring/rest/lo/get",
		data : JSON.stringify(activity)
	// success : activitySuccess(data)
	}).done(activitySuccess);
}
function fetchActivities(username) {
	var activity = new Object();
	activity.user = {
		"userid" : 2
	};
	activity.startDate = new Date(2014, 10, 15, 0, 0, 0, 0);
	activity.endDate = new Date(2014, 10, 25, 0, 0, 0, 0);
	$.ajax({
		type : "POST",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		url : "http://localhost:8080/hma-monitoring/rest/activity/get",
		data : JSON.stringify(activity)
	// success : activitySuccess(data)
	}).done(activitySuccess);
}
function activitySuccess(data) {
	if (data.status == "FAILURE") {
		$("#resp").text("Failure.." + data.responseMsg);
		return;
	}
	for (activity in data.activities) {
		$('#activityTable').append(
				"<tr><td>" + data.activities[activity].activityType.activity
						+ "</td><td>" + data.activities[activity].startDate
						+ "</td><td>" + data.activities[activity].endDate
						+ "</td><td>"
						+ data.activities[activity].caloriesBurned
						+ "</td></tr>");
	}
	$('#activityTable').DataTable({
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