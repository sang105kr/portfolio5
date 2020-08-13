package com.kh.portfolio.board.vo;

import javax.persistence.Entity;
import javax.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
public class CodeDecodeVO {
	@Positive(message="코드를 선택하세요")
	private String code;
	private String decode;
	
	public CodeDecodeVO(String code,String decode) {
		this.code = code;
		this.decode = decode;
	}
}
