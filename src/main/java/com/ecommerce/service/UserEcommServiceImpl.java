package com.ecommerce.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Address;
import com.ecommerce.entity.Customer;
import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.repository.UserRepository;

@Service
public class UserEcommServiceImpl implements UserEcommerceService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserRepository userRepository;

	public Customer commonFindUserById(Long id) {
		Customer user = userRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("User Not found with Given Id " + id, "E404"));
		logger.info("User Details :: " + user);
		return user;
	}

	@Override
	public List<Customer> getUserList() {
		List<Customer> list = userRepository.findAll();
		return list;
	}

	@Override
	public Customer getUserById(Long userId) {
		Customer userDetails = commonFindUserById(userId);
		logger.info("User Details :: " + userDetails);
		return userDetails;
	}

	@Override
	public Customer createNewUser(Customer user) {
	    return userRepository.save(user);
	}

	@Override
	public Customer updateUser(Customer user, Long id) {
		Customer userById = commonFindUserById(id);

		return userById;
	}

	@Override
	public String deleteUser(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
