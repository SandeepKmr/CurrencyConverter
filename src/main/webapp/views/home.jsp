<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="webjars/materializecss/1.0.0/css/materialize.min.css">
<link rel="stylesheet"
	href="webjars/font-awesome/5.3.1/css/fontawesome.css">
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">

<link rel="stylesheet" href="css/global_style.css">
<link rel="stylesheet" href="css/home.css">
<title>Home</title>
</head>
<body>



	<div class="" style="width: 100%">
		<div class="row">
			<div class="navbar-fixed">
				<!-- <span>C</span> <span>C</span> -->
				<nav class="col s12 " style="color:white;"> <!-- <div>CC</div>> -->
				<div class="nav-wrapper">
					<a href="#" class=" " style="font-size: 42px; font-weight: 600;">CC</a>
					<div style="display: inline-block ;margin-left:36%;position:relative;top:-10px;" class="welcome_user_block">
						<span>Welcome </span>
							<span style="position:relative;left:8px;">${pageContext.request.userPrincipal.name}</span>
					</div> 
					<ul id="nav-mobile" class="right ">
						<li><a href="/logout">Logout</a></li>
					</ul>
				</div>

				</nav>
			</div>
		</div>
		<div class="row converter_wrapper ">


			<div class="col s12 m6 l4 xl4 col-content currency_converter_block "
				style="padding: 10px;">


				<div class="block_header">
					<span class="header_txt" style="">Converter</span>


				</div>
				<div class="content_wrapper" style="padding: 10px 45px 10px 45px;">




					<form id="converter_form">

						<div class="row">



							<div class=" input-field col s12">
								<input placeholder="Amount" id="amount" name="amount"
									type="text" maxlength="8"> <label for="amount">Amount
								</label>

							</div>



						</div>


						<div class="row">
							<div class=" input-field col s12">

								<select id="fromCurrency" name="fromDropDown">
								</select><label>From</label>


							</div>


						</div>

						<div class="row">
							<div class=" input-field col s12">
								<select id="toCurrency" name="toDropDown">
								</select><label>To</label>

							</div>


						</div>
					</form>
					<div class="row">

						<div class=" input-field col s12">
							<button class="btn waves-effect waves-light" style="width: 100%"
								id="currency_convert_btn">Convert</button>
						</div>



					</div>

					<div class="row" id="currency_result"></div>
				</div>
			</div>
			<!--  <div class="col s1 m1 l1 xl1"></div>-->
			<div class="col s12 m6 l4 xl4 col-content" style="padding: 10px">

				<div class="block_header">
					<span class="header_txt" style="">Latest Exchange Rates</span>


				</div>

				<div class="content_wrapper" style="padding: 10px 25px 10px 25px;">



					<div class="row" style="padding: 0px 30px 0px 30px"
						id="latest_rates"></div>

					<div class="row" style="margin-top: 40px;">

						<div class="col s12  center-align">
							<a id="update_currency_rates_btn" class="update_currency_btn"
								style="">Click here to update latest record</a>
						</div>
					</div>
				</div>

			</div>

			<div class="col s12 m6 l4 xl4 " style="padding: 10px;">
				<div class="block_header">
					<span class="header_txt" style="">Latest Conversion History</span>


				</div>
				<div class="content_wrapper"
					style="height: 580px; overflow: scroll; padding: 10px 25px 10px 25px;">

					<div id="conversion_queries" style=""></div>
				</div>
			</div>


		</div>

		<footer class="page-footer">

		<div class="footer-copyright">
			<div class="container" style="text-align: center;">
				© 2018 Currency Converter <a class="grey-text text-lighten-4 right"
					href="#!"></a>
			</div>
		</div>
		</footer>
	</div>




	<script src="webjars/jquery/3.3.1-1/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js"></script>
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
	<script src="webjars/font-awesome/5.3.1/js/fontawesome.min.js"></script>
	<script type="text/javascript" src="js/home.js"></script>
</body>
</html>