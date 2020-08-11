package com.kh.portpolio.common.util;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kh.portfolio.common.page.RecordCriteria;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class RecordCriteriaTest {
	private final static Logger logger
	= LoggerFactory.getLogger(RecordCriteriaTest.class);
	
	@Inject
	RecordCriteria recordCriteria;
	
	
	@Test
	void test() {
		recordCriteria.setReqPage(3);
		recordCriteria.setRecNumPerPage(10);
		
		
		logger.info("시작레코드:" + recordCriteria.getStarRec());
		logger.info("종료레코드:" + recordCriteria.getEndRec());
		
	}
}




