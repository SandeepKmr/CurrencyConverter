/**
 * Sandeep kumar
 */

$(document).ready(function() {
	$('select').formSelect();
	getConversionQueries();
	getLatestCurrencyRates();
	$("#update_currency_rates_btn").click(function() {
		getCurrentCurrencyRates();
	});


	$("#currency_convert_btn").click(function() {

		var amount = $("#amount").val();
		var fromCurrency = $("#fromCurrency").val();
		var toCurrency = $("#toCurrency").val();
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "/currency/convert/" + amount + "/" + fromCurrency + "/" + toCurrency,
			dataType : 'json',
			success : function(response) {
				console.log(response)
				displayConvertedResult(response);
				//$('#currency_result').html("<div class='col s10 result_box' ><div >" + response + "</div></div>");
			},
			error : function(e) {}
		});
	});



});


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
		htmlString += '<div class="card horizontal"><div class="card-stacked"><div class="card-content">'
			+ '<p>' + value.fromCurrency + '</p></div></div></div>';
	});
	
	console.log("QueryResult"+queryList);
	$('#conversion_queries').html(htmlString);

}