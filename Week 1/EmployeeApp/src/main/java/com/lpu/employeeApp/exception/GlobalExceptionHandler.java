package com.lpu.employeeApp.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<Map<String,String>> handleArithematicEx(ArithmeticException ex) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Error",ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
	}
	
	@ExceptionHandler(CompanyNotFoundException.class)
	public ResponseEntity<Map<String,String>> handleCompanyNotFoundEx(CompanyNotFoundException ex) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Error",ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
	}
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<Map<String,String>> handleEmployeeNotFoundEx(EmployeeNotFoundException ex){
		Map<String ,String> map = new HashMap<String,String>();
		map.put("Error", ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> map = new HashMap<String, String>();
		
		ex.getBindingResult().getFieldErrors()
		.forEach(error -> map.put(error.getField(), error.getDefaultMessage()));
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
	}
	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<Map<String,String>> handleEx(Exception ex){
//		Map<String ,String> map = new HashMap<String,String>();
//		map.put("Error", ex.getMessage());
//		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
//	}

}
