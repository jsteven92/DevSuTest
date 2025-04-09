package com.devsu.infraestructure.exceptions;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devsu.infraestructure.exceptions.dto.ErrorMessage;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
	
	@ExceptionHandler(LocalNotFoundException.class)
	@ResponseBody
	public ResponseEntity<ErrorMessage> localNotFoundException(LocalNotFoundException exception){
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<ErrorMessage> GeneralExcepcion(Exception ex) {
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, "Internal Error");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }
}
