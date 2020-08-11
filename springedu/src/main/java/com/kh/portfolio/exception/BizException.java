package com.kh.portfolio.exception;

public class BizException extends RuntimeException {

	public BizException() {}
	public BizException(String msg) {
		super(msg);
	}
	public BizException(Throwable t) {
		super(t);
	}
}
