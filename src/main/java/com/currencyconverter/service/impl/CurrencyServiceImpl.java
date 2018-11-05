package com.currencyconverter.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.currencyconverter.model.ConversionRates;
import com.currencyconverter.model.Currency;
import com.currencyconverter.repository.CurrencyRepository;
import com.currencyconverter.service.CurrencyService;

/**
 * 
 * @author sandeepkumar
 *
 */
@Service
@PropertySource("api.properties")
public class CurrencyServiceImpl implements CurrencyService {

	private static List<String> CURRENCY_LIST = null;

	static {
		CURRENCY_LIST = new ArrayList<String>();
		CURRENCY_LIST.add("INR");
		CURRENCY_LIST.add("EUR");
		CURRENCY_LIST.add("AUD");
		CURRENCY_LIST.add("USD");
		CURRENCY_LIST.add("GBP");
		CURRENCY_LIST.add("SGD");
		CURRENCY_LIST.add("CAD");

	}

	@Autowired
	private CurrencyRepository currencyRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${api.latestRates}")
	private String latestCurrencyRates;

	@Override
	public List<Currency> getLatestRates() {
		List<Currency> currencyRateList = new ArrayList<>();
		ConversionRates conversionRate = getDataFromApi();
		Map<String, BigDecimal> currencyRates = conversionRate.getRates();
		CURRENCY_LIST.forEach(currency -> {
			currencyRateList.add(new Currency(currency, currencyRates.get(currency)));

		});

		return currencyRepository.saveAll(currencyRateList);

	}

	// Accesing latest currency rates data from openexchangerates.org
	public ConversionRates getDataFromApi() {
		ConversionRates convRates = restTemplate.getForEntity(latestCurrencyRates, ConversionRates.class).getBody();
		return convRates;
	}

}
