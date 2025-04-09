package com.devsu.infraestructure.exceptions;

public class LocalNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LocalNotFoundException(String message) {
		super(message);
	}
}
