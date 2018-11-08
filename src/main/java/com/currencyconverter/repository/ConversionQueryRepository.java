package com.currencyconverter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.currencyconverter.model.ConversionQuery;

public interface ConversionQueryRepository extends JpaRepository<ConversionQuery, Long> {

	List<ConversionQuery> findTop10ByEmailIdOrderByQueryDateDesc(String username);
}
