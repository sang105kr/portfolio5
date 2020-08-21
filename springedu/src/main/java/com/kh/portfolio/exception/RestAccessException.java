package com.kh.portfolio.exception;

import java.util.List;

public class RestAccessException extends RuntimeException {

	List<ErrorMsg> errorMsgList;
	ErrorMsg errorMsg;
	
	public RestAccessException() {}
	public RestAccessException(String msg) {
		super(msg);
	}
	public RestAccessException(ErrorMsg errorMsg) {
		this.errorMsg = errorMsg;
	}
	public RestAccessException(List<ErrorMsg> errorMsgList) {
		this.errorMsgList = errorMsgList;
	}
	public RestAccessException(Throwable t) {
		super(t);
	}
	public List<ErrorMsg> getErrorMsgList() {
		return errorMsgList;
	}
	public ErrorMsg getErrorMsg() {
		return errorMsg;
	}
	
}
