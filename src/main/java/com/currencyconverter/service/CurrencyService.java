package com.currencyconverter.service;

import java.util.List;

import com.currencyconverter.model.ConversionQuery;
import com.currencyconverter.model.ConversionRates;
import com.currencyconverter.model.Currency;

/**
 * 
 * @author sandeepkumar
 *
 */
public interface CurrencyService {

	public ConversionRates getDataFromApi();

	public List<String> getAllCurrencies();

	public List<Currency> getLatestRates();

	public String convertCurrency(String amount, String fromCurrency, String toCurrency);

	public List<ConversionQuery> getConversionQueries(String emailId);

}
