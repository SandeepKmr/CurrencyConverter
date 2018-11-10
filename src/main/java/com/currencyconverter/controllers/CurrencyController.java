package com.currencyconverter.controllers;

import java.math.BigDecimal;
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

	@GetMapping("/currencies")
	public List<String> getAllCurrencies() {

		return CurrenciesList.LIST_OF_CURRENCIES;
	}

	@GetMapping("currency/latest-currency-rates")
	@Cacheable("latest-currency-rates")
	public List<Currency> getLatestCurrencyRates() {

		List<Currency> currencyList = currencyService.getLatestRates();

		currencyList.forEach(cur -> {
			System.out.println("CurrencyName-----" + cur.getCurrencyName() + "        Rate----" + cur.getRate());
		});
		return currencyList;
	}

	@GetMapping("currency/convert/{amount}/{fromCurrency}/{toCurrency}")
	public BigDecimal convertCurrency(@PathVariable("amount") String amount,
			@PathVariable("fromCurrency") String fromCurrency, @PathVariable("toCurrency") String toCurrency) {

		BigDecimal convertedAmount = currencyService.convertCurrency(amount, fromCurrency, toCurrency);
//		if(1==1)
//		{
//			throw new IllegalAccessError("Access error");
//		}
		
		return convertedAmount;
	}

	@GetMapping("currency/conversion-queries")
	public List<ConversionQuery> getConversionQueries(Principal principal) {

		return currencyService.getConversionQueries(principal.getName());
	}

}
