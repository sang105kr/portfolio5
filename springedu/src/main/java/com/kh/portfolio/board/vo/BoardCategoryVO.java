package com.kh.portfolio.board.vo;

import javax.persistence.Entity;
import javax.validation.constraints.Positive;

import lombok.Data;

@Entity
@Data
public class BoardCategoryVO {
	
	@Positive(message="분류를 선택하세요")
	private long cid;				//	CID	NUMBER(10,0)	No		1	분류코드
	private String cname;		//	CNAME	VARCHAR2(60 BYTE)	No		2	분류명
}
