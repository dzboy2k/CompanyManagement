package com.vti.academy.companymanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter	@Setter	private int statusCode;
	@Getter	@Setter	private String messageCode;
	@Getter	@Setter	private String message;
}
