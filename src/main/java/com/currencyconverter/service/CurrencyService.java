package com.currencyconverter.service;

import java.util.List;

import com.currencyconverter.model.Currency;

/**
 * 
 * @author sandeepkumar
 *
 */
public interface CurrencyService {

	public List<Currency> getLatestRates();

}
