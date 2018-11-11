package com.currencyconverter.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides the static list of currencies for display and calulation purpose.
 * 
 * @author sandeepkumar
 *
 */
public class CurrenciesList {
	public static List<String> LIST_OF_CURRENCIES = null;

	static {
		LIST_OF_CURRENCIES = new ArrayList<String>();
		LIST_OF_CURRENCIES.add("INR");
		LIST_OF_CURRENCIES.add("EUR");
		LIST_OF_CURRENCIES.add("AUD");
		LIST_OF_CURRENCIES.add("USD");
		LIST_OF_CURRENCIES.add("GBP");
		LIST_OF_CURRENCIES.add("SGD");
		LIST_OF_CURRENCIES.add("CAD");

	}
}
