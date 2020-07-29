package com.kh.portfolio.board.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BoardVO {

	private long bnum;													//	BNUM			NUMBER(10,0)	No		1	"게시글번호	"
	private BoardCategoryVO boardCategoryVO;		//	BCATEGORY	NUMBER(10,0)	Yes		2	분류카테고리
	private String btile;												//	BTITLE		VARCHAR2(150 BYTE)	Yes		3	제목
	private String bid;													//	BID				VARCHAR2(40 BYTE)	Yes		4	작성자ID
	private String nickname;										//	BNICKNAME	VARCHAR2(30 BYTE)	Yes		5	별칭
	private Timestamp bdate;										//	BCDATE		TIMESTAMP(6)	Yes	"systimestamp "	6	작성일
	private Timestamp udate;										//	BUDATE		TIMESTAMP(6)	Yes	"systimestamp "	7	수정일
	private int hit;														//	BHIT			NUMBER(5,0)	Yes	"0 "	8	조회수
	private String bcontent;										//	BCONTENT	CLOB	Yes		9	본문내용
	private int bgroup;													//	BGROUP		NUMBER(5,0)	Yes		10	답글그룹
	private int bstep;													//	BSTEP			NUMBER(5,0)	Yes		11	답변글의 단계
	private int bindent;												//	BINDENT		NUMBER(5,0)	Yes		12	답변글의 들여쓰기
	
}
