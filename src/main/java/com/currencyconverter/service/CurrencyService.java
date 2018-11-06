package com.currencyconverter.service;

import java.math.BigDecimal;
import java.util.List;

import com.currencyconverter.model.ConversionRates;
import com.currencyconverter.model.Currency;

/**
 * 
 * @author sandeepkumar
 *
 */
public interface CurrencyService {

	public ConversionRates getDataFromApi();
	public List<Currency> getLatestRates();
	public BigDecimal convertCurrency(String amount,String fromCurrency,String toCurrency);

}
