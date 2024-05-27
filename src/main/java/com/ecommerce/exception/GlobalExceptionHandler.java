package com.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<GlobalErrorResponse> getException(ProductNotFoundException ex) {
		GlobalErrorResponse globalErrorResponse = new GlobalErrorResponse(ex.getMessage(), ex.getCode());
		return new ResponseEntity<>(globalErrorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserAlreadyExist.class)
	public ResponseEntity<GlobalErrorResponse> getExceptionEntity(UserAlreadyExist ex) {
		GlobalErrorResponse errorResponse = new GlobalErrorResponse(ex.getMessage(), ex.getCode());
		return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	}
}
