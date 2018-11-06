/**
 * 
 */

$(document).ready(function() {
	$('ul.tabs').tabs();
	//   $('ul.tabs').tabs({ 'swipeable': true });
	$('.datepicker').datepicker();
	$("#login_btn").click(function() {
		var email = $("#login_email").val();
		var password = $("#login_password").val();
		console.log("login_btn clicked");

	});

	$("#signup_btn").click(function() {

		console.log("signup_btn clicked");

	});




});



function validateLogin() {
}