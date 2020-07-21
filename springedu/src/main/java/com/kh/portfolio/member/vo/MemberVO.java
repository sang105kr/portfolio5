package com.kh.portfolio.member.vo;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class MemberVO {
	private String 		id;					//	ID	VARCHAR2(40 BYTE)	No		1	이메일 ex)admin@google.com
	private String 		pw;					//	PW	VARCHAR2(10 BYTE)	No		2	8~10자리, 특수문자 포함
	private String 		tel;				//	TEL	VARCHAR2(13 BYTE)	Yes		3	'-'포함 ex)010-1234-5678
	private String 		nickname;		//	NICKNAME	VARCHAR2(30 BYTE)	Yes		4	별칭
	private String 		gender;			//	GENDER	CHAR(3 BYTE)	Yes		5	성별 ex)'남','녀'
	private String 		region;			//	REGION	VARCHAR2(30 BYTE)	Yes		6	지역
	private Date			birth;			//	BIRTH	DATE	Yes		7	생년월일
	private Timestamp cdate;			//	CDATE	TIMESTAMP(6)	Yes	"systimestamp "	8	생성일시
	private Timestamp udate;			////	UDATE	TIMESTAMP(6)	Yes		9	수정일시
	
	//회원 이미지 처무용
	//PIC	BLOB	Yes		10	사진, 확장자 jpg,gif,png

}
