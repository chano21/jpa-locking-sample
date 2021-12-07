package kr.pco.core.spring;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import kr.pco.core.code.ResponseCode;
import kr.pco.core.exception.PcoException;

@RestControllerAdvice
public class ExceptionAdviser {

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	private ResponseEntity<String> invalidDataException(MethodArgumentNotValidException e) {
		for(StackTraceElement stack :  e.getStackTrace()) {
			System.out.println(stack.toString());
		}
		List<String> fields = new ArrayList<String>();
		for (FieldError error : e.getBindingResult().getFieldErrors()) {
			fields.add(error.getField());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
	

	@ExceptionHandler({ Exception.class })
	private ResponseEntity<String> serverErrorExeption(Exception e) {
		for(StackTraceElement stack :  e.getStackTrace()) {
			System.out.println(stack.toString());
		}
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(ResponseCode.SERVER_ERROR.getMessage());
	}

	@ExceptionHandler({MissingServletRequestParameterException.class})
	private ResponseEntity<String> invalidDataException(MissingServletRequestParameterException e){
		for(StackTraceElement stack :  e.getStackTrace()) {
			System.out.println(stack.toString());
		}
		List<String> fields = new ArrayList<String>();
		  fields.add(e.getParameterName());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		
	}	
	
	@ExceptionHandler(NoHandlerFoundException.class)
	protected ResponseEntity<String> handleNoHandlerFoundException(NoHandlerFoundException e,
			HttpServletRequest request) {
		for(StackTraceElement stack :  e.getStackTrace()) {
			System.out.println(stack.toString());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}

	@ExceptionHandler({ PcoException.class })
	private ResponseEntity<String> FriendException(PcoException e) {
		for(StackTraceElement stack :  e.getStackTrace()) {
			System.out.println(stack.toString());
		}
		 return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
	}


}
