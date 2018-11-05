package com.currencyconverter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.currencyconverter.model.Role;

/**
 * 
 * @author sandeepkumar
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
