package com.eom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.eom.dto.CustomHttpResponseDto;


@RestControllerAdvice
public class GlobalException {
	
	private static final String USER_NOT_FOUND="User not found";
	private static final String USERNAME_EXIST="Username have already taken";
	private static final String EMAIL_EXIST="Email have already taken";
	private static final String WRONG_PASSWORD="You have enter wrong password";
	
	
	@ExceptionHandler(EmailExitException.class)
	public ResponseEntity<CustomHttpResponseDto> emailExitsException(EmailExitException e){
		return createHttpResponse(HttpStatus.BAD_REQUEST,EMAIL_EXIST);
	}
	

	@ExceptionHandler(UserNameExitsException.class)
	public ResponseEntity<CustomHttpResponseDto> userNameExistException(UserNameExitsException e){
		return createHttpResponse(HttpStatus.BAD_REQUEST, USERNAME_EXIST);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<CustomHttpResponseDto> usrNotFoundException(UserNotFoundException e){
		return createHttpResponse(HttpStatus.BAD_REQUEST,e.getMessage());
	}
	
	@ExceptionHandler(WrongPasswordException.class)
	public ResponseEntity<CustomHttpResponseDto> wrongPasswordException(WrongPasswordException e){
		return createHttpResponse(HttpStatus.BAD_REQUEST, WRONG_PASSWORD);
	}
	
	@ExceptionHandler(UserLockedException.class)
	public ResponseEntity<CustomHttpResponseDto> accountIsLocked(UserLockedException e){
		return createHttpResponse(HttpStatus.FORBIDDEN, e.getMessage());
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<CustomHttpResponseDto> UsernameNotFoundExceptionHandler(UsernameNotFoundException e){
		return createHttpResponse(HttpStatus.BAD_REQUEST, e.getMessage());
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<CustomHttpResponseDto> nullPointexception(NullPointerException e){
		return createHttpResponse(HttpStatus.BAD_REQUEST, e.getMessage());
	}
	
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<CustomHttpResponseDto> badCredentials(BadCredentialsException e){
		return createHttpResponse(HttpStatus.BAD_REQUEST, e.getMessage());
	}
	
	
	
	private ResponseEntity<CustomHttpResponseDto> createHttpResponse(HttpStatus httpStatus, String message) {
		CustomHttpResponseDto httpResponse=new CustomHttpResponseDto(httpStatus.value(),httpStatus,httpStatus.getReasonPhrase().toUpperCase(),message);
		return new ResponseEntity<CustomHttpResponseDto>(httpResponse,httpStatus);
	}

	



	
}
