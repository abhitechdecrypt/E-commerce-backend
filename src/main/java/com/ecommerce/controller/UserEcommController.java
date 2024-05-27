package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.ApiResponseDTO;
import com.ecommerce.entity.Customer;
import com.ecommerce.service.UserEcommServiceImpl;

@RestController
@RequestMapping("api/v1/ecommerce/customer")
public class UserEcommController {

	@Autowired
	private UserEcommServiceImpl userEcommServiceImpl;
	
	@GetMapping("getUserlist")
	public ResponseEntity<?> getUserData(){
		List<Customer> list = userEcommServiceImpl.getUserList();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	
	@PostMapping("createNewUser")
	public ResponseEntity<?> createNewUser(@RequestBody Customer user){
		ApiResponseDTO<Customer> response = new ApiResponseDTO<>();
		Customer newUser = userEcommServiceImpl.createNewUser(user);
		if(newUser != null) {
			response.setMessage("User Created Successfully");
			response.setCode("S202");
			response.setData(newUser);
		}else {
			response.setMessage("Failed To create the user");
			response.setCode("500");
			response.setData(newUser);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
	}
}
