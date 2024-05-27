package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entity.Customer;

public interface UserRepository extends JpaRepository<Customer, Long>{

	boolean existsByEmail(String email);

	boolean existsByPhone(String phone);
	
}
