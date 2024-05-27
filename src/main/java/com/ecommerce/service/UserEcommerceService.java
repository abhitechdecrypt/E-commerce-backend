package com.ecommerce.service;

import java.util.List;

import com.ecommerce.entity.Customer;

public interface UserEcommerceService {
	
	List<Customer> getUserList();
	
	Customer getUserById (Long productId);
	
	Customer createNewUser(Customer user);
	
	Customer updateUser(Customer user, Long id);
	
	String deleteUser(Long id);
	
}
