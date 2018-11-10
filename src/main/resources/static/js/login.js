/**
 * 
 */

$(document).ready(function() {

	validateLoginForm();

});


function validateLoginForm() {
	$("form[name='login_form']").validate({
		errorElement : "div",
		errorPlacement : function(error, element) {
			var placement = $(element).data('error');
			if (placement) {
				$(placement).append(error)
			} else {
				error.insertAfter(element);
			}
		},
		// validation rules
		rules : {
			// The key name on the left side is the name attribute
			// of an input field. Validation rules are defined
			// on the right side

			email : {
				required : true,
				email : true
			},
			password : {
				required : true,
				//minlength : 8,
				//strongPassword : true
			}
		},
		// validation error messages
		messages : {
			email : "Please enter a valid email address",
			password : {
				required : "Please provide a password",
				minlength : "Password must be at least 8 characters long",
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	});
}