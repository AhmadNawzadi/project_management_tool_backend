package com.bluewater.project_management_tool.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserException {
	
	private final String message;
	private final Throwable throwable;
	private final HttpStatus httpStatus;
	

}
