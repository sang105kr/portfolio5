package com.kh.portfolio.board.svc;

import java.util.List;

import com.kh.portfolio.board.vo.RboardVO;
import com.kh.portfolio.board.vo.VoteVO;

public interface RboardSVC {
	
	//댓글작성
	int write(RboardVO rboardVO);
	//댓글수정
	int modify(RboardVO rboardVO);
	//댓글삭제
	int delete(Long rnum);
	//댓글 조회
	RboardVO replyView(long rnum);	
	//댓글목록
	List<RboardVO> list();
	//대댓글 작성
	int reply(RboardVO rboardVO);	
	//댓글 호감, 비호감
	//투표이력 없으면 추가 있으면 변경
	int vote(VoteVO voteVO);	
}
