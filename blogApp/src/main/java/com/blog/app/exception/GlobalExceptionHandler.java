package com.blog.app.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDetails>UserExceptionHandler(UserException ex){
		ErrorDetails error = new ErrorDetails();
		
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(ex.getMessage());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CommentException.class)
	public ResponseEntity<ErrorDetails>CommentExceptionHandler(CommentException ex){
		ErrorDetails error = new ErrorDetails();
		
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(ex.getMessage());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PostException.class)
	public ResponseEntity<ErrorDetails>PostExceptionHandler(PostException ex){
		ErrorDetails error = new ErrorDetails();
		
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(ex.getMessage());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CategoryException.class)
	public ResponseEntity<ErrorDetails>CategoryExceptionHandler(CategoryException ex){
		ErrorDetails error = new ErrorDetails();
		
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(ex.getMessage());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> MethodArgumentNotValidExceptionHandler (MethodArgumentNotValidException ex){
		
		Map<String, String> response = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)-> {
			String feildName = ((FieldError) error).getField();
		String message = error.getDefaultMessage();
		response.put(feildName, message);
		} );
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}
