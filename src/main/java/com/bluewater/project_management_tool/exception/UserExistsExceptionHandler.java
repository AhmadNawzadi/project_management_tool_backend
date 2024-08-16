package com.bluewater.project_management_tool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExistsExceptionHandler {
	
	@ExceptionHandler(value = {UserAlreadyExistsException.class})
	public ResponseEntity<Object> handleEntityAlreadyExistsException(UserAlreadyExistsException ex){
		
		UserException userException = new UserException(
				ex.getMessage(),
				ex.getCause(),
				HttpStatus.CONFLICT);
		
		return new ResponseEntity<>(userException, HttpStatus.CONFLICT);
	}
	
//	@ExceptionHandler(value = {UserNotFoundException.class})
//	public ResponseEntity<Object> handleUserNotFoundException
//		(UserNotFoundException userNotFoundException) {
//		
//		UserException userException = new UserException(
//				userNotFoundException.getMessage(),
//				userNotFoundException.getCause(),
//				HttpStatus.NOT_FOUND
//		);
//		
//		return new ResponseEntity<>(userException, HttpStatus.NOT_FOUND);
//	}

}
