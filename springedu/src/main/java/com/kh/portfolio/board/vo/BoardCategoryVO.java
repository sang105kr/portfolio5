package com.kh.portfolio.board.vo;

import lombok.Data;

@Data
public class BoardCategoryVO {
	private long cid;				//	CID	NUMBER(10,0)	No		1	분류코드
	private String cname;		//	CNAME	VARCHAR2(60 BYTE)	No		2	분류명
}
