package com.kh.portpolio.common.util;

import javax.inject.Inject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kh.portfolio.common.page.PageCriteria;
import com.kh.portfolio.common.page.RecordCriteria;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class PageCriteriaTest {
	
	private final static Logger logger
	= LoggerFactory.getLogger(PageCriteriaTest.class);
	
	@Inject
	RecordCriteria recordCriteria;
	
	@Inject
	PageCriteria pageCriteria;
	
	@Test
	@DisplayName("페이징구현")
	void paging() {
		
		recordCriteria.setReqPage(1);						//요청이지
		recordCriteria.setRecNumPerPage(10);		//한페이지에보여줄 레코드수
		
		pageCriteria.setPageNumPerPage(10); 		//한페이지에보여줄 페이지수
		pageCriteria.setRc(recordCriteria);
		pageCriteria.setTotalRec(440);					//전체 게시된 건수.
		pageCriteria.calculatePaging();         //페이징 계산
		
		//시작페이지
		logger.info("시작페이지:" + pageCriteria.getStartPage());
		logger.info("종료페이지:" + pageCriteria.getEndPage());
		logger.info("최종페이지:" + pageCriteria.getFinalEndPage());
		logger.info("다음페이지:" + pageCriteria.isNext());
		logger.info("이전페이지:" + pageCriteria.isPrev());
		
		
	}
}






