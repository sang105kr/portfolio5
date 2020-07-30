package com.kh.portfolio.board.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BoardFileVO {
	private long fid;					//	FID	NUMBER(10,0)	No		1	파일아이디
	private long bnum;				//	BNUM	NUMBER(10,0)	Yes		2	게시글번호
	private String fname;			//	FNAME	VARCHAR2(150 BYTE)	Yes		3	파일명
	private long fsize;				//	FSIZE	VARCHAR2(45 BYTE)	Yes		4	파일크기
	private String ftype;			//	FTYPE	VARCHAR2(50 BYTE)	Yes		5	파일유형
	private byte[] fdata;			//	FDATA	BLOB	Yes		6	첨부파일
	private Timestamp cdate;	//	CDATE	TIMESTAMP(6)	Yes	"systimestamp "	7	작성일
	private Timestamp udate;	//	UDATE	TIMESTAMP(6)	Yes	"systimestamp "	8	수정일
}
