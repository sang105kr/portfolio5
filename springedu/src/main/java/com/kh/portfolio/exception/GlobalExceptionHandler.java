package com.kh.portfolio.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//Controller 계층에서 감지된 예외를 별도의 클래스에서 모아 처리하고자 할때 사용
@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger 
		= LoggerFactory.getLogger(GlobalExceptionHandler.class);

	// DAO 계층에서 SQLException예외 발생시 처리
	@ExceptionHandler(DataAccessException.class)
	public ModelAndView handleSQLException(HttpServletRequest request, Exception ex) {
		logger.error("Request: " + request.getRequestURL() + " called " + ex);

		ModelAndView mav = new ModelAndView();
		mav.addObject("url", request.getRequestURL());
		mav.addObject("exception", ex);
		mav.setViewName("exception/exception");
		return mav;
	}

	//사용자 정의 예오처리
	@ExceptionHandler(BizException.class)
	public ModelAndView handleBizException(HttpServletRequest request, Exception ex) {
		logger.error("Request: " + request.getRequestURL() + " called " + ex);

		ModelAndView mav = new ModelAndView();
		mav.addObject("url", request.getRequestURL());
		mav.addObject("exception", ex);
		mav.setViewName("exception/bizException");
		return mav;
	}	
}
