package com.currencyconverter.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.currencyconverter.model.ConversionQuery;
import com.currencyconverter.model.Currency;
import com.currencyconverter.service.CurrencyService;
import com.currencyconverter.utils.CurrenciesList;

/**
 * 
 * @author sandeepkumar
 *
 */

@RestController
public class CurrencyController {

	@Autowired
	private CurrencyService currencyService;

	/**
	 * Fetches list of currencies.
	 * 
	 * @return
	 */
	@GetMapping("/currencies")
	public List<String> getAllCurrencies() {

		return CurrenciesList.LIST_OF_CURRENCIES;
	}

	/**
	 * Fetches latest currency rates form https://openexchangerates.org/api/
	 * api's and store it in database.
	 */
	@GetMapping("currency/latest-currency-rates")
	@Cacheable("latest-currency-rates")
	public List<Currency> getLatestCurrencyRates() {

		List<Currency> currencyList = currencyService.getLatestRates();
		return currencyList;
	}

	/**
	 * Convert amount from fromCurrency to toCurrency.
	 * 
	 * @param amount
	 * @param fromCurrency
	 * @param toCurrency
	 * @return
	 */
	@GetMapping("currency/convert/{amount}/{fromCurrency}/{toCurrency}")
	public String convertCurrency(@PathVariable("amount") String amount,
			@PathVariable("fromCurrency") String fromCurrency, @PathVariable("toCurrency") String toCurrency) {

		String convertedAmount = currencyService.convertCurrency(amount, fromCurrency, toCurrency);

		return convertedAmount;
	}

	/**
	 * Provides list of last ten queried conversions.
	 */
	@GetMapping("currency/conversion-queries")
	public List<ConversionQuery> getConversionQueries(Principal principal) {

		return currencyService.getConversionQueries(principal.getName());
	}

}
