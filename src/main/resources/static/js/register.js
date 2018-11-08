$(document).ready(function() {
	$('.datepicker').datepicker();
	validateRegisterForm();

});
//this.optional(element)
function validateRegisterForm() {
	console.log("register validation !!");
	var passwordReg = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@#$!%*?&])[A-Za-z\d@#$!%*?&]{8,20}$/

	$.validator.addMethod('strongPassword', function(value, element) {
		return passwordReg.test(value);
	}, 'Password must contain at least one capital letter,one number and one special character(@#$!%*?&).'),

	$.validator.addMethod("dateFormat",
		function(value, element) {
			return Date.parse(value) <= Date.parse('12/31/2000') || value == "";
		},
		"Date of birth should should be less than 31-12-2000 .");

	$("form[name='register_form']").validate({
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


			userName : {
				required : true,
				minlength : 8,
				maxlength : 15,
			},
			emailId : {
				required : true,
				email : true
			},
			password : {
				required : true,
				minlength : 8,
				maxlength : 20,
				strongPassword : true
			},

			confirmPassword : {
				required : true,
				minlength : 8,
				maxlength : 20,
				equalTo : "#signup_pass"
			},
			dateOfBirth : {
				required : true,
				dateFormat : true
			}
		},
		// validation error messages
		messages : {
			userName : {
				required : "Please enter a valid email username.",
				minlength : "Username must be at least 8 characters long.",
				maxlength : "Username should not exceed 15 characters."
			},
			emailId : {
				required : "Please enter a valid email address.",
				maxlength : "EmailId should not exceed 40 characters."
			},
			password : {
				required : "Please enter a password.",
				minlength : "Password must be at least 8 characters long.",
				maxlength : "Password should not exceed 20 characters.",
			},
			confirmPassword : {
				required : "Please enter confirm password.",
				equalTo : "Confirm password and password should be same .",
			},
			dateOfBirth : {
				required : "Please enter your date of birth.",
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	});
}