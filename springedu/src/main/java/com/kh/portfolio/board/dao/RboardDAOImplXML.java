package com.kh.portfolio.board.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.kh.portfolio.board.vo.RboardVO;

@Repository
public class RboardDAOImplXML implements RboardDAO {
	
	private static final Logger logger 
		= LoggerFactory.getLogger(RboardDAOImplXML.class);
	
	//댓글작성
	@Override
	public int write(RboardVO rboardVO) {
		return 0;
	}
	//댓글수정
	@Override
	public int modify(RboardVO rboardVO) {
		return 0;
	}
	//댓글삭제
	@Override
	public int delete(Long rnum) {
		return 0;
	}
	//댓글목록
	@Override
	public List<RboardVO> list() {
		return null;
	}

}
