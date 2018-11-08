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

			<span class="login_title">Login</span>


			<div class="row">

				<div id="login" class="col s12"
					style="">


					<div class="row">
						<form action="/login" method="POST" class="col s12" name="login_form">
							<div class="row">
								<div class="input-field col s12">
									<input id="email" type="text" class="validate" name="email">
									<label for="email">Email </label>
								</div>

							</div>
							<div class="row">
								<div class="input-field col s12">
									<input id="password" type="password" 
										name="password"> <label for="password">Password</label>
								</div>
							</div>
							<div class="row">

								<div class="input-field col s12">
									<button class="btn waves-effect waves-light login_btn_size"
										type="submit" id="login_btn" name="submit">LogIn</button>
								</div>
							</div>
						</form>
					</div>

				</div>
				<div class="col s12 signup_text">
					<span>Don't have an account? <a href="/register">Sign up</a></span>
				</div>




			</div>
		</div>
	</div>







	<script src="webjars/jquery/3.3.1-1/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js"></script>
	
	<!-- <script src="webjars/bootstrap/4.1.3/js/bootstrap.js"></script> -->
	<script src="webjars/materializecss/1.0.0/js/materialize.min.js"></script>
	<!-- <script src="webjars/font-awesome/5.3.1/js/fontawesome.min.js"></script> -->
	<!-- <script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.4/umd/popper.min.js"></script> -->
	<script type="text/javascript" src="js/login.js"></script>
</body>
</html>