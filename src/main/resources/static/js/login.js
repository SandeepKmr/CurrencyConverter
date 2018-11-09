/**
 * 
 */

$(document).ready(function() {

	validateLoginForm();
	//	$("#login_btn").click(function() {
	//		//validateLogin();
	//	});


});


function validateLoginForm() {
//	$.validator.addMethod('strongPassword', function(value, element) {
//		return this.optional(element)
//			|| value.length >= 6
//			&& /\d/.test(value)
//			&& /[a-z]/i.test(value);
//	}, 'Invalid password')


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