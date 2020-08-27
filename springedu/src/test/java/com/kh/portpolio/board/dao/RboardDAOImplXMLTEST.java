package com.kh.portpolio.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kh.portfolio.board.dao.RboardDAO;
import com.kh.portfolio.board.vo.RboardVO;
import com.kh.portfolio.board.vo.Vote;
import com.kh.portfolio.board.vo.VoteVO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class RboardDAOImplXMLTEST {
	private final static Logger logger
	= LoggerFactory.getLogger(RboardDAOImplXMLTEST.class);
	
	@Inject
	RboardDAO rboardDAO;
	
//	//댓글작성
//	int write(RboardVO rboardVO);
	@Test
	@DisplayName("댓글작성")
//	@Disabled
	void write() {
		RboardVO rboardVO = new RboardVO();
		rboardVO.setBnum(1130);
		rboardVO.setRid("test3@test.com");
		rboardVO.setRcontent("1130게시글에 대한 댓글 테스트중");
		rboardDAO.write(rboardVO);		
		
		rboardVO = rboardDAO.replyView(rboardVO.getRnum());
		logger.info(rboardVO.toString());
	}
	
//	//댓글수정
//	int modify(RboardVO rboardVO);
	@Test
	@DisplayName("댓글수정")
	@Disabled
	void modify() {
		long rnum = 24;
		
		RboardVO rboardVO = new RboardVO();
		rboardVO.setRnum(rnum);
		rboardVO.setBnum(1129);
		rboardVO.setRid("test@test.com");
		rboardVO.setRcontent("1129게시글에 대한 42번 댓글 수정 테스트중");
		
		rboardDAO.modify(rboardVO);
		RboardVO rboardVO2 = rboardDAO.replyView(rnum);
		
		Assertions.assertEquals(rboardVO.getRcontent(),
														rboardVO2.getRcontent());
	}

//	//댓글삭제
//	int delete(Long rnum);
	@Test
	@DisplayName("댓글 삭제")
	@Disabled
	void delete() {
		int cnt = rboardDAO.delete((long)25);
		
		Assertions.assertEquals(1, cnt);
	}
//	//댓글목록
//	List<RboardVO> list();
	@Test
	@DisplayName("댓글목록")
	@Disabled
	void list() {
		List<RboardVO> list = rboardDAO.list();
		
		for(RboardVO rboardVO : list) {
			logger.info(rboardVO.toString());
		}
	}
	
			
//	//대댓글 작성
//	int reply(RboardVO rboardVO);
//	//이전댓글 step업데이트
//	int updateStep(int rgroup, int rstep);
	
	
//	//댓글 호감, 비호감
//	//투표이력 없으면 추가 있으면 변경
//	int vote(VoteVO voteVO);	
	@Test
	@DisplayName("호감도투표")
	void vote() {
		VoteVO voteVO = new VoteVO();
		
		voteVO.setRnum((long)24);
		voteVO.setBnum((long)1129);
		voteVO.setRid("test@test.com");
		voteVO.setVote(Vote.BAD);
		
		rboardDAO.vote(voteVO);
	}
	
}
















