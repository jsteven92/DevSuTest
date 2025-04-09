package com.devsu.bank.infraestructure.exception;

public class LocalNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LocalNotFoundException(String message) {
		super(message);
	}
}
