<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="webjars/materializecss/1.0.0/css/materialize.min.css">
<link rel="stylesheet"
	href="webjars/font-awesome/5.3.1/css/fontawesome.css">

<link rel="stylesheet"
	href="http://github.hubspot.com/odometer/themes/odometer-theme-default.css" />
<script src="http://github.hubspot.com/odometer/odometer.js"></script>
<link rel="stylesheet" href="css/global_style.css">
<link rel="stylesheet" href="css/home.css">
<title>Home</title>
</head>
<body style="background-color: #f2f2f2">
	<div class="" style="width: 100%">
		<div class="row">
			<nav class="col s12 ">
			<div class="nav-wrapper">
				<a href="#" class="brand-logo">CC</a>
				<%-- <div>${pageContext.request.userPrincipal.name}</div> --%>
				<ul id="nav-mobile" class="right ">
					<!-- <form action="/logout" method="get" style="margin-top: 12px;">
						<button class=" btn orange darken-4" type="submit">Logout</button>
					</form> -->
					<li><a href="/logout">Logout</a></li>
				</ul>
			</div>

			</nav>
		</div>
		<div class="row converter_wrapper ">
			<div class="col s4 currency_converter_block "
				style="border-right: 1px solid #e0e0e0; min-height: 540px;">
				<div class="row">

					<div class=" input-field col s10">
						<input placeholder="Amount" id="amount" type="text"> <label
							for="amount">Amount </label>

					</div>



				</div>


				<div class="row">
					<div class=" input-field col s10">

						<select class="icons" id="fromCurrency">

							<option value="INR" data-icon="images/currency/inr.svg">INR</option>
							<option value="EUR" data-icon="images/currency/eur.svg">EUR</option>
							<option value="AUD" data-icon="images/currency/aud.svg">AUD</option>
							<option value="USD" data-icon="images/currency/usd.svg">USD</option>
							<option value="GBP" data-icon="images/currency/gbp.svg">GBP</option>
							<option value="SGD" data-icon="images/currency/sgd.svg">SGD</option>
							<option value="CAD" data-icon="images/currency/cad.svg">CAD</option>
						</select><label>From</label>


					</div>


				</div>

				<div class="row">
					<div class=" input-field col s10">
						<select class="icons" id="toCurrency">

							<option value="INR" data-icon="images/currency/inr.svg">INR</option>
							<option value="EUR" data-icon="images/currency/eur.svg">EUR</option>
							<option value="AUD" data-icon="images/currency/aud.svg">AUD</option>
							<option value="USD" data-icon="images/currency/usd.svg">USD</option>
							<option value="GBP" data-icon="images/currency/gbp.svg">GBP</option>
							<option value="SGD" data-icon="images/currency/sgd.svg">SGD</option>
							<option value="CAD" data-icon="images/currency/cad.svg">CAD</option>
						</select><label>To</label>

					</div>


				</div>

				<div class="row">
					
					<div class=" input-field col s10" >
						<button class="btn waves-effect waves-light" type="submit" style="width:100%"
							name="action" id="currency_convert_btn">Convert</button>
					</div>

					

				</div>
				<div class="row"  id="currency_result">
				
				
				</div>

				




			</div>
			<div class="col s4 "
				style="border-right: 1px solid #e0e0e0; min-height: 540px;">



				<div class="row" style="text-align: center">
					<h6 class="">Current Exchange Rates</h6>
					<button class="btn waves-effect waves-light" type="submit"
						id="update_currency_rates_btn" name="action">Update
						Records</button>
				</div>
				<div class="row" style="padding: 0px 30px 0px 30px"
					id="latest_rates"></div>


			</div>


			<div class="col s4 " style="height: 540px; overflow: scroll;">
				<div class="row" style="text-align: center">
					<h6 class="header">Last Searches History</h6>
				</div>



				<div class="col s12">
					<div class="card-panel teal">
						<span class="white-text">I am a very simple card. I am good
							at containing small bits of information. I am convenient because
							is called a panel in other frameworks. </span>
					</div>
				</div>



				<div class="col s12">

					<div class="card horizontal">

						<div class="card-stacked">
							<div class="card-content">
								<p>I am a very simple card. I am good at containing small
									bits of information.</p>
							</div>

						</div>
					</div>
				</div>
				<div class="col s12">

					<div class="card horizontal">

						<div class="card-stacked">
							<div class="card-content">
								<p>I am a very simple card. I am good at containing small
									bits of information.</p>
							</div>

						</div>
					</div>
				</div>
				<div class="col s12">

					<div class="card horizontal">

						<div class="card-stacked">
							<div class="card-content">
								<p>I am a very simple card. I am good at containing small
									bits of information.</p>
							</div>

						</div>
					</div>
				</div>


				<div class="col s12">

					<div class="card horizontal">

						<div class="card-stacked">
							<div class="card-content">
								<p>I am a very simple card. I am good at containing small
									bits of information.</p>
							</div>

						</div>
					</div>
				</div>


				<div class="col s12">

					<div class="card horizontal">

						<div class="card-stacked">
							<div class="card-content">
								<p>I am a very simple card. I am good at containing small
									bits of information.</p>
							</div>

						</div>
					</div>
				</div>


				<div class="col s12">

					<div class="card horizontal">

						<div class="card-stacked">
							<div class="card-content">
								<p>I am a very simple card. I am good at containing small
									bits of information.</p>
							</div>

						</div>
					</div>
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
	<!-- <script src="webjars/bootstrap/4.1.3/js/bootstrap.js"></script> -->
	<script src="webjars/materializecss/1.0.0/js/materialize.min.js"></script>
	<script src="webjars/font-awesome/5.3.1/js/fontawesome.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.4/umd/popper.min.js"></script>
	<script type="text/javascript" src="js/home.js"></script>
</body>
</html>