package com.kh.portfolio.board.vo;

import lombok.Data;

@Data
public class VoteVO {
	private long rnum;			//	RNUM	NUMBER(10,0)	No		1	댓글번호
	private long bnum;			//	BNUM	NUMBER(10,0)	No		2	최초원글
	private String rid;			//	RID	VARCHAR2(40 BYTE)	No	3	댓글아이디
	private Vote vote;      //	VOTE	CHAR(1 BYTE)	No		4	'1':호감,'2':비호감
}
