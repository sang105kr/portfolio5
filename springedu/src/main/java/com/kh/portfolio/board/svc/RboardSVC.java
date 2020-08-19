package com.kh.portfolio.board.svc;

import java.util.List;

import com.kh.portfolio.board.vo.RboardVO;

public interface RboardSVC {
	
	//댓글작성
	int write(RboardVO rboardVO);
	//댓글수정
	int modify(RboardVO rboardVO);
	//댓글삭제
	int delete(Long rnum);
	//댓글목록
	List<RboardVO> list();
	
}
