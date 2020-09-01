package com.kh.portfolio.board.dao;

import java.util.List;

import com.kh.portfolio.board.vo.RboardVO;
import com.kh.portfolio.board.vo.VoteVO;

public interface RboardDAO {
	//댓글작성
	int write(RboardVO rboardVO);
	//댓글수정
	int modify(RboardVO rboardVO);
	//댓글삭제
	int delete(Long rnum);
	//댓글 조회
	RboardVO replyView(long rnum);
	
	//댓글목록
	List<RboardVO> list(long bnum, long startRec, long endRec);
	
	//대댓글 작성
	int reply(RboardVO rboardVO);
	//이전댓글 step업데이트
	int updateStep(int rgroup, int rstep);
	//댓글 호감, 비호감
	//투표이력 없으면 추가 있으면 변경
	int vote(VoteVO voteVO);
	
	//게시글 총 레코드 수 
	int totalRecordCount(long bnum);	
	int totalRecordCount(String searchType, String keyword, long bnum);
	
}
