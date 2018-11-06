package com.currencyconverter.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.currencyconverter.model.ConversionQuery;
import com.currencyconverter.model.Currency;
import com.currencyconverter.service.CurrencyService;

/**
 * 
 * @author sandeepkumar
 *
 */

@RestController
@RequestMapping("/currency")
public class CurrencyController {

	@Autowired
	private CurrencyService currencyService;

	@GetMapping("/latest-currency-rates")
	public List<Currency> getLatestCurrencyRates() {

		List<Currency> currencyList = currencyService.getLatestRates();

		currencyList.forEach(cur -> {
			System.out.println("CurrencyName-----" + cur.getCurrencyName() + "        Rate----" + cur.getRate());
		});
		return currencyList;
	}

	@GetMapping("/convert/{amount}/{fromCurrency}/{toCurrency}")
	public BigDecimal convertCurrency(@PathVariable ("amount")String amount, @PathVariable("fromCurrency")String fromCurrency,
			@PathVariable("toCurrency") String toCurrency) {

		BigDecimal convertedAmount = currencyService.convertCurrency(amount, fromCurrency, toCurrency);

		return convertedAmount;
	}

	
	@GetMapping("/conversion-queries")
	public List<ConversionQuery> getConversionQueries()
	{
		
		
		return null;
	}
}
