<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="webjars/materializecss/1.0.0/css/materialize.min.css">
<link rel="stylesheet" href="css/global_style.css">
<link rel="stylesheet" href="css/register.css">
<link rel="stylesheet" href="css/login.css">
<title>Register</title>
</head>
<body>


	<div class="register_container">

		<div class="register_wrapper">

			<span class="login_title">Register</span>
			<div id="signUp" class="col s12" style="height: 560px;">



				<div class="row">
					<div class="col s12">
						<form:form method="POST" action="/register"
							modelAttribute="registrationForm" name="register_form">

							<div class="row">
								<div class="input-field col s12">
									<form:input id="signup_email" path="emailId" type="email"
										name="emailId" />
									<form:label for="signup_email" path="emailId">Email </form:label>
									<form:errors path="emailId" cssClass="form_error" />
								</div>

							</div>
							<div class="row">
								<div class="input-field col s12">
									<form:input id="signup_pass" path="password" type="password"
										name="password" />
									<form:label path="password" for="signup_pass">Password</form:label>
									<form:errors path="password" cssClass="form_error" />
								</div>
							</div>

							<div class="row">
								<div class="input-field col s12">
									<form:input id="signup_confirm_pass" path="confirmPassword"
										type="password" name="confirmPassword" />
									<form:label for="signup_confirm_pass" path="confirmPassword">Confirm Password</form:label>
									<form:errors path="confirmPassword" cssClass="form_error" />
								</div>
							</div>
							<div class="row">
								<form:label for="signup_dob" path="dateOfBirth"
									style="position: relative;left: 10px;top: 10px;font-size: 15px;">Date Of Birth</form:label>
								<div class="input-field col s12">
									<form:input id="signup_dob" path="dateOfBirth" type="date"
										class="browser-default" name="dateOfBirth" style="width:100%;height:42px;color: #545454;" />

									<form:errors path="dateOfBirth" cssClass="form_error" />
								</div>

							</div>

							<div class="row">
								<div class=" col s12">
									<button class="btn waves-effect waves-light" type="submit"
										id="signup_btn" name="submit"
										style="width: 100%; margin-top: 20px;">SignUp</button>
								</div>
							</div>

							<div class="reg_error">${registration_status}</div>


						</form:form>

					</div>
				</div>
				<div class="col s12 signin_text">
					<span>Have an account? <a href="/login">Log in</a></span>
				</div>


			</div>

		</div>
	</div>







	<script src="webjars/jquery/3.3.1-1/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js"></script>
	<script src="webjars/materializecss/1.0.0/js/materialize.min.js"></script>
	<script type="text/javascript" src="js/register.js"></script>
</body>
</html>