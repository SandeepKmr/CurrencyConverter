/**
 * Sandeep kumar
 */

$(document).ready(function() {
	loadCurrencyDropdown();
	getConversionQueries();
	getLatestCurrencyRates();
	$("#update_currency_rates_btn").click(function() {
		getLatestCurrencyRates();
	});


	jQuery.validator.addMethod("currencyNonEqual", function(value, element) {
		var fromCurrency = $("#fromCurrency").val();
		return value != fromCurrency;
	}, "To and From currencies should be different.");

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
				number : true,
				min : 1,
			},

			toDropDown : {
				currencyNonEqual : true,
			}
		},
		// validation error messages
		messages : {
			amount : {
				required : "Please enter an amount.",
				min : "Amount should be greater than 0.",
				number : "Amount should be a number.",
			},
		},
		submitHandler : function(form) {
			return false;
		}
	});
	$("#currency_convert_btn").click(function() {
		$("#currency_result").addClass("hide")
		var amount = $("#amount").val();
		var fromCurrency = $("#fromCurrency").val();
		var toCurrency = $("#toCurrency").val();



		if ($('#converter_form').valid()) {
			$.ajax({
				type : "GET",
				contentType : "application/json",
				url : "/currency/convert/" + amount + "/" + fromCurrency + "/" + toCurrency,
				dataType : 'json',
				success : function(data, status, xhr) {
					displayConvertedResult(data);
				},
				error : function(xhr, status, error) {
					displayError(xhr);


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
		success : function(data, status, xhr) {
			currenciesList = data;
			for (var i = 0; i < currenciesList.length; i++) {

				options += '<option value="' + currenciesList[i] + '"  data-icon="images/currency/' + currenciesList[i] + '.svg" >'
					+ currenciesList[i] + '</option>';
			}
			$("#fromCurrency").append(options);
			$("#fromCurrency").material_select();

			var toOptions = '';

			for (var i = currenciesList.length - 1; i >= 0; i--) {

				toOptions += '<option value="' + currenciesList[i] + '"  data-icon="images/currency/' + currenciesList[i] + '.svg" >'
					+ currenciesList[i] + '</option>';
			}

			$("#toCurrency").append(toOptions);
			$("#toCurrency").material_select();



		},
		error : function(xhr, status, error) {
			displayError(xhr)
		}
	});
}
function getLatestCurrencyRates() {
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/currency/latest-currency-rates",
		dataType : 'text',
		success : function(data, status, xhr) {
			displayCurrentRates(data);
		},
		error : function(xhr, status, error) {
			
			displayError(xhr);
		}
	});

}

function getConversionQueries() {
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/currency/conversion-queries",
		dataType : 'text',
		success : function(data, status, xhr) {
			displayLatestQueries(data);
		},
		error : function(xhr, status, error) {
			displayError(xhr);
			
		}
	});

}

function displayConvertedResult(response) {
	$("#currency_result").removeClass("hide");
	var htmlString = "<div class='col s12 result_box' ><div >" + response + "</div></div>";
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
	
	console.log(response.length);
	
	if (queryList.length >0) {
		$.each(queryList, function(key, value) {
			htmlString += '<div class="history_box"><div class="history_header"><div class="row"><div class="from_currency_box col s4 m5 l4 xl3 valign-wrapper">'
				+ '<img src="images/currency/' + value.fromCurrency + '.svg"> <span>' + value.fromCurrency + '</span></div><div class="arrow_box col s4 m2 l3 xl2 valign-wrapper"style="height: 30px;">'
				+ '<i class="fa fa-long-arrow-right"></i></div><div class="to_currency_box col s4 m5 l4 xl3 valign-wrapper"><img src="images/currency/' + value.toCurrency + '.svg">'
				+ '<span>' + value.toCurrency + '</span></div></div></div><div class="history_body"><div class="history_amount_box">'
				+ '<span>Amount :</span><span style="margin-left: 10px;">' + value.amount + '</span></div><div class="history_result_box"><span>Result :</span>'
				+ '<span style="margin-left: 10px;">' + value.convertedResult + '</span></div><div class="right-align history_date_time"><span>' + moment(value.queryDate).format('llll') + '</span></div></div></div>';

		});
	}
	else
	{
		htmlString='<span class="no_records">No records found.</span>';
	}

	
	
	$('#conversion_queries').html(htmlString);
}

function displayError(xhr)
{
	Materialize.toast(xhr.responseJSON.message, 4000, '#e57373 red lighten-2')
}
