package com.currencyconverter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.currencyconverter.model.User;
/**
 * 
 * @author sandeepkumar
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmailId(String emailId);
}
