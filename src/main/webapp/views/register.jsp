<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- <link rel="stylesheet" href="webjars/bootstrap/4.1.3/css/bootstrap.css"> -->
<link rel="stylesheet"
	href="webjars/materializecss/1.0.0/css/materialize.min.css">
<!-- <link rel="stylesheet"
	href="webjars/font-awesome/5.3.1/css/fontawesome.css"> -->


<link rel="stylesheet" href="css/global_style.css">
<link rel="stylesheet" href="css/login.css">
<title>Login</title>
</head>
<body>


	<div class="login_container">

		<div class="login_wrapper">

			<span class="login_title">Register</span>
			<div id="signUp" class="col s12" style="height: 530px;">



				<div class="row">
					<div class="col s12">
						<form:form method="POST" action="/register"
							modelAttribute="registrationForm">
							<div class="row">
								<div class="input-field col s12">
									<form:input   id="signup_username" path="userName" type="text" class="validate"/>
										<form:label path="userName" for="signup_username">Username </form:label>
								</div>

							</div>
							<div class="row">
								<div class="input-field col s12">
									<form:input id="signup_email" path="emailId" type="email" class="validate"/>
										<form:label for="signup_email" path="emailId">Email </form:label>
								</div>

							</div>
							<div class="row">
								<div class="input-field col s12">
									<form:input id="signup_pass" path="password" type="password" class="validate"/>
										<form:label path="password" for="signup_pass">Password</form:label>
								</div>
							</div>

							<div class="row">
								<div class="input-field col s12">
									<form:input id="signup_confirm_pass"  path="confirmPassword" type="password"
										class="validate"/>
										<form:label for="signup_confirm_pass" path="confirmPassword">Confirm Password</form:label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s12">
									<form:input id="signup_dob"  path="dateOfBirth" type="text" class="datepicker"/>
										<form:label for="signup_dob" path="dateOfBirth">Date Of Birth</form:label>
								</div>

							</div>

							<div class="row">
								<div class=" col s12">
									<button class="btn waves-effect waves-light" type="submit"
										id="signup_btn" name="submit"
										style="width: 100%; margin-top: 20px;">SignUp</button>
								</div>
							</div>

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
	<!-- <script src="webjars/bootstrap/4.1.3/js/bootstrap.js"></script> -->
	<script src="webjars/materializecss/1.0.0/js/materialize.min.js"></script>
	<!-- <script src="webjars/font-awesome/5.3.1/js/fontawesome.min.js"></script> -->
	<!-- <script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.4/umd/popper.min.js"></script> -->
	<script type="text/javascript" src="js/login.js"></script>
</body>
</html>