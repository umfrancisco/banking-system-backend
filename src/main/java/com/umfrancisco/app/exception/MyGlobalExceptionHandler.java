package com.umfrancisco.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyGlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> myResourceNotFoundException(ResourceNotFoundException e) {
		String message = e.getMessage();
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<String> myAPIException(ApiException e) {
		String message = e.getMessage();
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}
}
