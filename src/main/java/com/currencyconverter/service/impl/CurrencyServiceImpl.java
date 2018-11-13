package com.currencyconverter.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.currencyconverter.model.ConversionQuery;
import com.currencyconverter.model.ConversionRates;
import com.currencyconverter.model.Currency;
import com.currencyconverter.repository.ConversionQueryRepository;
import com.currencyconverter.repository.CurrencyRepository;
import com.currencyconverter.service.CurrencyService;
import com.currencyconverter.utils.CurrenciesList;

/**
 * 
 * @author sandeepkumar
 *
 */
@Service
@Transactional
public class CurrencyServiceImpl implements CurrencyService {

	private static final Logger logger = LoggerFactory.getLogger(CurrencyServiceImpl.class);

	@Autowired
	private CurrencyRepository currencyRepository;

	@Autowired
	private ConversionQueryRepository conversionQueryRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${api.latestRates}")
	private String latestCurrencyRates;

	@Override
	public List<String> getAllCurrencies() {

		return CurrenciesList.LIST_OF_CURRENCIES;
	}

	@Override
	public List<Currency> getLatestRates() {
		List<Currency> currencyRateList = new ArrayList<>();
		ConversionRates conversionRate = getDataFromApi();
		Map<String, BigDecimal> currencyRates = conversionRate.getRates();
		getAllCurrencies().forEach(currency -> {
			currencyRateList.add(new Currency(currency, currencyRates.get(currency)));

		});
		return currencyRepository.saveAll(currencyRateList);

	}

	/**
	 * Accesing latest currency rates data from openexchangerates.org
	 */
	@Override
	public ConversionRates getDataFromApi() {
		ConversionRates convRates = restTemplate.getForEntity(latestCurrencyRates, ConversionRates.class).getBody();
		logger.info("fetched latest currency rates from openexchange api.");
		return convRates;
	}

	/**
	 * Converting the currency
	 */
	@Override
	public String convertCurrency(String amount, String fromCurrency, String toCurrency) {
		BigDecimal conversionAmount = null;
		List<Currency> currencyList = null;

		if (StringUtils.isNotBlank(amount) && StringUtils.isNotBlank(fromCurrency)
				&& StringUtils.isNotBlank(toCurrency)) {

			conversionAmount = new BigDecimal(amount);
			if (conversionAmount.compareTo(BigDecimal.ZERO) > 0) {

				currencyList = currencyRepository.findAll();
				Map<String, BigDecimal> currencyRateMap = currencyList.stream()
						.collect(Collectors.toMap(Currency::getCurrencyName, Currency::getRate));

				BigDecimal fromCurrencyValue = currencyRateMap.get(fromCurrency);
				BigDecimal toCurrencyValue = currencyRateMap.get(toCurrency);
				conversionAmount = (toCurrencyValue.divide(fromCurrencyValue, 4, RoundingMode.FLOOR))
						.multiply(conversionAmount);
				logger.info("currency converted  successfully.Converted amount " + conversionAmount.toString());

			} else {
				
				logger.error("amount should not be 0");
				throw new IllegalArgumentException("amount should not be 0 or less than 0");
			}
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			ConversionQuery query = new ConversionQuery(auth.getName(), fromCurrency, toCurrency, amount,
					conversionAmount.toString(), new Date());
			conversionQueryRepository.save(query);

		} else {
			logger.error("invalid conversion parameters.");
			throw new IllegalArgumentException("invalid conversion parameters");
		}
		return conversionAmount.toString();
	}

	/**
	 * Fetches  last 10 queried conversions.
	 */
	@Override
	public List<ConversionQuery> getConversionQueries(String emailId) {

		return conversionQueryRepository.findTop10ByEmailIdOrderByQueryDateDesc(emailId);
	}

}
