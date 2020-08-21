package com.kh.portfolio.exception;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//RestController 계층에서 감지된 예외를 별도의 클래스에서 모아 처리하고자 할때 사용
@RestControllerAdvice
public class RestExceptionHandler{

	private static final Logger logger 
		= LoggerFactory.getLogger(RestExceptionHandler.class);
	
	@ExceptionHandler(RestAccessException.class)
  public ResponseEntity<List<ErrorMsg>> haldler(HttpServletRequest request, RestAccessException ex) {
		logger.error("Request: " + request.getRequestURL() + " called " + ex);
    return new ResponseEntity<>(ex.getErrorMsgList(), HttpStatus.BAD_REQUEST);
  }

}
