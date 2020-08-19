package com.kh.portfolio.board.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class RboardVO {
	private long rnum;						//	RNUM	NUMBER(10,0)	No		1	댓글번호
	private long bnum;						//	BNUM	NUMBER(10,0)	No		2	최초원글
	private String rid;						//	RID	VARCHAR2(40 BYTE)	No		3	"댓글작성자ID"
	private String rnickname;			//	RNICKNAME	VARCHAR2(20 BYTE)	Yes		4	댓글작성자별칭
	private Timestamp rcdate;			//	RCDATE	DATE	No	SYSTIMESTAMP 	5	작성일시
	private Timestamp rudate;			//	RUDATE	DATE	Yes	"SYSTIMESTAMP "	6	수정일시
	private String rcontnet;			//	RCONTENT	CLOB	No		7	댓글본문내용
	
	//선호도
	private int rgood;						//	RGOOD	NUMBER(5,0)	Yes		8	선호
	private int rbad;							//	RBAD	NUMBER(5,0)	Yes		9	비선호
	
	//댓글그룹
	private int rgroup;						//	RGROUP	NUMBER(5,0)	Yes		10	댓글그룹
	private int rstep;						//	RSTEP	NUMBER(5,0)	Yes		11	댓글 단계
	private int rindent;					//	RINDENT	NUMBER(5,0)	Yes		12	댓글 들여쓰기
	
	//부모댓글 참조
	private long prnum;						//	PRNUM	NUMBER(10,0)	Yes		13	부모댓글번호
	private String prid;					//	PRID	VARCHAR2(40 BYTE)	Yes		14	부모댓글아이디
	private String pnickname;			//	PRNICNAME	VARCHAR2(30 BYTE)	Yes		15	부대댓글작성자(별칭)
	
	//프로파일 이미지
	private String ftype;					//파일의 mime타입   image/png
	private byte[] pic;						//첨부이미지파일의 바이트 배열
}


