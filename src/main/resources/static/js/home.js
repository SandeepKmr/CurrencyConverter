/**
 * Sandeep kumar
 */

$(document).ready(function() {
	loadCurrencyDropdown();
	validateConverter();
	getConversionQueries();
	getLatestCurrencyRates();
	$("#update_currency_rates_btn").click(function() {
		getLatestCurrencyRates();
	});


	$("#converter_form").validate({
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
			amount : {
				required : true,
			},
		},
		// validation error messages
		messages : {
			amount : {
				required : "Please enter an amount.",
				minlength : "Password must be at least 8 characters long.",
			},
		},
		submitHandler : function(form) {
			alert('valid form');
			return false;
		}
	});
	$("#currency_convert_btn").click(function() {
		console.log("form currency" + $('#fromCurrency').valid());

		//$('input[name="amount"]').valid();
		var amount = $("#amount").val();
		var fromCurrency = $("#fromCurrency").val();
		var toCurrency = $("#toCurrency").val();
		
		if(fromCurrency == toCurrency)
			{
			$('#error_message').html("should be different !!")
			return;
			}
		
		if ($('#converter_form').valid() && fromCurrency != toCurrency) {
			$.ajax({
				type : "GET",
				contentType : "application/json",
				url : "/currency/convert/" + amount + "/" + fromCurrency + "/" + toCurrency,
				dataType : 'json',
				success : function(response) {
					console.log(response)
					displayConvertedResult(response);
				},
				error : function(e) {

					console.log(e)
				}
			});

		}
		

	});



});

function loadCurrencyDropdown() {
	var currenciesList = [];
	var options = '';
	$("#fromCurrency").empty();
	$("#toCurrency").empty();

	$("#fromCurrency").html(' ');
	$("#toCurrency").html(' ');
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/currencies",
		dataType : 'json',
		success : function(response) {
			currenciesList = response;
			console.log(response.length);
			console.log(response);
			for (var i = 0; i < currenciesList.length; i++) {

				options += '<option value="' + currenciesList[i] + '"  data-icon="images/currency/' + currenciesList[i] + '.svg" >'
					+ currenciesList[i] + '</option>';
			}
			$("#fromCurrency").append(options);
			$("#fromCurrency").material_select();
		
//			options = '';
//			
//			for (var i = currenciesList.length; i >0; i++) {
//
//				options += '<option value="' + currenciesList[i] + '"  data-icon="images/currency/' + currenciesList[i] + '.svg" >'
//					+ currenciesList[i] + '</option>';
//			}
//			
//			$("#toCurrency").append(options);
//			$("#toCurrency").material_select();
//			
			
			
		},
		error : function(e) {
			console.log(e.responseText);
		}
	});
}
function getLatestCurrencyRates() {
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/currency/latest-currency-rates",
		dataType : 'text',
		success : function(response) {
			displayCurrentRates(response);
		},
		error : function(e) {}
	});

}

function getConversionQueries() {
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/currency/conversion-queries",
		dataType : 'text',
		success : function(response) {
			displayLatestQueries(response);
		},
		error : function(e) {}
	});

}

function displayConvertedResult(response) {
	var htmlString = "<div class='col s10 result_box' ><div >" + response + "</div></div>";
	$('#currency_result').html(htmlString);
	getConversionQueries();

}
function displayCurrentRates(response) {
	var currencyList = JSON.parse(response);
	var htmlString = "";
	$.each(currencyList, function(key, value) {
		htmlString += '<div class="section"> <div class="row"><div class="col s2"><img src="images/currency/' + value.currencyName + '.svg" height="25">'
			+ '</div><div class="col s3"><span >' + value.currencyName + '</span></div><div class="col s1"></div><div class="col s5"><span  >' + value.rate + '</span>'
			+ '</div></div></div><div class="divider"></div>'
	});
	$('#latest_rates').html(htmlString);
}


function displayLatestQueries(response) {
	var queryList = JSON.parse(response);
	var htmlString = "";
	$.each(queryList, function(key, value) {




		htmlString += '<div class="history_box"><div class="history_header"><div class="row"><div class="from_currency_box col s4 m5 l4 xl3 valign-wrapper">'
			+ '<img src="images/currency/' + value.fromCurrency + '.svg"> <span>' + value.fromCurrency + '</span></div><div class="arrow_box col s4 m2 l3 xl2 valign-wrapper"style="height: 30px;">'
			+ '<i class="fa fa-long-arrow-right"></i></div><div class="to_currency_box col s4 m5 l4 xl3 valign-wrapper"><img src="images/currency/' + value.toCurrency + '.svg">'
			+ '<span>' + value.toCurrency + '</span></div></div></div><div class="history_body"><div class="history_amount_box">'
			+ '<span>Amount :</span><span style="margin-left: 10px;">' + value.amount + '</span></div><div class="history_result_box"><span>Result :</span>'
			+ '<span style="margin-left: 10px;">' + value.convertedResult + '</span></div><div class="right-align history_date_time"><span>' + moment(value.queryDate).format('llll') + '</span></div></div></div>';

	});


	$('#conversion_queries').html(htmlString);

}


function validateConverter() {
	$("form[name='converter_form']").validate({
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
			amount : {
				required : true,
			},
		},
		// validation error messages
		messages : {
			amount : {
				required : "Please enter an amount.",
			}
		},
		submitHandler : function(form) {
			return false;
		}
	});
}