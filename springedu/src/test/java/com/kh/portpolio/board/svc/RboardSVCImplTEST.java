package com.kh.portpolio.board.svc;

import javax.inject.Inject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kh.portfolio.board.svc.RboardSVC;
import com.kh.portfolio.board.vo.RboardVO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class RboardSVCImplTEST {
	
	private final static Logger logger
	= LoggerFactory.getLogger(RboardSVCImplTEST.class);
	
	@Inject
	RboardSVC rboardSVC;
	
	@Test
	@DisplayName("대댓글작성")
	void reply() {
		
		RboardVO rboardVO = new RboardVO();
		
		rboardVO.setPrnum((long)81);
		rboardVO.setBnum((long)1130);
		rboardVO.setRid("test3@test.com");
		rboardVO.setRcontent("81번댓글에 대한 대댓글 작성테스트");
		
		rboardSVC.reply(rboardVO);
		
	}
}












