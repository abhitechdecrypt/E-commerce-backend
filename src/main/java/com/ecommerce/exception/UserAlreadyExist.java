package com.ecommerce.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserAlreadyExist  extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String message;
	private String code;

}
