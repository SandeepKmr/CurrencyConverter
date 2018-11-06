package com.currencyconverter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.currencyconverter.model.ConversionQuery;

public interface ConversionQueryRepository extends JpaRepository<ConversionQuery, Long> {

}
