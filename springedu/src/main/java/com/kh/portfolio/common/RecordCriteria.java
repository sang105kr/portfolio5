package com.kh.portfolio.common;

import org.springframework.stereotype.Component;

import lombok.Data;

/*
 * 요청 페이지를 입력받아 시작레코드, 종료레코드 구하기
 * 한페이지에 보여줄 레코드수 : 10이라 가정..
 */
@Component
@Data
public class RecordCriteria {
	
	private int reqPage;			//요청페이지
	private int numPerPage;		//한페이지당 보여줄 레코드수

	//시작레코드 = (요청페이지-1) * 한페이지에 보여줄 레코드수+1
	public int getStarRec() {
		
		return (reqPage - 1) * numPerPage + 1;
	}
	
	//종료레코드 = 요청페이지 X 한페이지에보여줄 레코드수
	public int getEndRec() {
		return reqPage * numPerPage;
	}
	
}
