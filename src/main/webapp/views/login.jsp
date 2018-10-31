<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

			<span class="login_title">Welcome</span>





			<!-- <div class="row">
				<form class="col s12">
					<div class="row">
						<div class="input-field col s12">
							<input  id="login_email" type="email"
								class="validate"> <label for="login_email">Email
							</label>
						</div>

					</div>
					<div class="row">
						<div class="input-field col s12">
							<input id="login_pass" type="password" class="validate">
							<label for="login_pass">Password</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s4"></div>
						<div class="input-field col s4">
							<button class="btn waves-effect waves-light" type="submit"
								name="action">
								Submit <i class="material-icons right">send</i>
							</button>
						</div>
						<div class="input-field col s4"></div>
					</div>
				</form>
			</div>
		</div>
	</div> -->








			<div class="row" >
				<div class="col s12">
					<ul class="tabs">
						<li class="tab col s6"><a class="active" href="#login">Login</a></li>
						<li class="tab col s6"><a href="#signUp">Sign Up </a></li>
					</ul>
				</div>


				<!-- Login Tab -->
				<div id="login" class="col s12" style="margin-top:20px;">


					<div class="row">
						<form class="col s12">
							<div class="row">
								<div class="input-field col s12">
									<input id="login_email" type="email" class="validate">
									<label for="login_email">Email </label>
								</div>

							</div>
							<div class="row">
								<div class="input-field col s12">
									<input id="login_pass" type="password" class="validate">
									<label for="login_pass">Password</label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s4"></div>
								<div class="input-field col s4">
									<button class="btn waves-effect waves-light" type="submit"
										name="action">
										Submit <i class="material-icons right">send</i>
									</button>
								</div>
								<div class="input-field col s4"></div>
							</div>
						</form>
					</div>

				</div>



				<!-- Sign Up tab -->
				<div id="signUp" class="col s12" style="margin-top:20px;">



					<div class="row">
						<form class="col s12">
							<div class="row">
								<div class="input-field col s12">
									<input id="signup_email" type="email" class="validate">
									<label for="signup_email">Email </label>
								</div>

							</div>
							<div class="row">
								<div class="input-field col s12">
									<input id="signup_pass" type="password" class="validate">
									<label for="signup_pass">Password</label>
								</div>
							</div>

							<div class="row">
								<div class="input-field col s12">
									<input id="signup_confirm_pass" type="password"
										class="validate"> <label for="signup_confirm_pass">Confirm
										Password</label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s12">
									<input id="signup_dob" type="text" class="datepicker">
									<label for="signup_dob">Date Of Birth</label>
								</div>

							</div>

							<div class="row">
								<div class=" col s4"></div>
								<div class=" col s4">
									<button class="btn waves-effect waves-light" type="submit"
										name="action">
										Submit <i class="material-icons right">send</i>
									</button>
								</div>
								<div class=" col s4"></div>
							</div>
							
							
							
						</form>
					</div>



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