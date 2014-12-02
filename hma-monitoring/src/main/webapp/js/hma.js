/**
 * 
 */
var userDetails = new Object();
$.cookie.json = true;
$(document).ready(function() {
	if (window.location.pathname != "/hma-monitoring/Login.html") {
		userDetails = $.cookie('userDetails');
		if (typeof userDetails == "undefined") {
			window.location.replace("Login.html");
			return;
		}
		
	}
	
});