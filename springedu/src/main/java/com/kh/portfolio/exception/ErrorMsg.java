package com.kh.portfolio.exception;

import lombok.Data;

@Data
public class ErrorMsg {
	private String fieldName; 			//오류 파라미터
	private String requestValue;		//요청값
	private String errMsg;					//오류메세지
}
