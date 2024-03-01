package org.jsp.hospitalmangementApp.exception;

import org.jsp.hospitalmangementApp.dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HospitalAdminExceptionHandler {

	@ExceptionHandler(AdminExceptions.class)
	public ResponseEntity<ResponseStructure<String>> handleAE(AdminExceptions adminExceptions) {

		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData(adminExceptions.getMessage());
		structure.setMessage("Admin not found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);

	}
}
