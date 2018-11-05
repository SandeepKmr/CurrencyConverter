package com.currencyconverter.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.currencyconverter.model.ConversionRates;
import com.currencyconverter.model.Currency;
import com.currencyconverter.service.CurrencyService;

/**
 * 
 * @author sandeepkumar
 *
 */

@RestController
@PropertySource("api.properties")
public class CurrencyController {

	@Autowired
	private CurrencyService currencyService;

	@GetMapping("/currency/latest-currency-rates")
	public List<Currency> getLatestCurrencyRates() {

		List<Currency> currencyList = currencyService.getLatestRates();

		currencyList.forEach(cur -> {
			System.out.println("CurrencyName-----" + cur.getCurrencyName() + "        Rate----" + cur.getRate());
		});
		return currencyList;
	}

	@GetMapping("currency/convert/{amount}/{to}/{from}")
	public BigDecimal convertCurrency() {
		
		
		return null;
	}

}
