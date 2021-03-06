package com.currencyconverter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.currencyconverter.model.Currency;
/**
 * 
 * @author sandeepkumar
 *
 */
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

}
