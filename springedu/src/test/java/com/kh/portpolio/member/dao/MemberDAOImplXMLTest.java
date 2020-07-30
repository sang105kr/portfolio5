package com.kh.portpolio.member.dao;

import java.sql.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kh.portfolio.member.dao.MemberDAO;
import com.kh.portfolio.member.vo.MemberVO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class MemberDAOImplXMLTest {

	private final static Logger logger
		= LoggerFactory.getLogger(MemberDAOImplXMLTest.class);
	
	@Inject
	//@Qualifier : spring 컨테이너에 동일타입의 bean이 존재할경우 명시적으로 참조하고자하는 bean을 지정할때사용
	//spring컨테이너에서 관리되는 bean이름은 특별히 지정해주지 않으면 클래스명을 기본값으로 갖는다(첫글자는 소문자)
	@Qualifier("memberDAOImplXML")
	MemberDAO memberDAO;	

	@Test			//테스트 대상에서 포함할때
	@DisplayName("회원등록")
	@Disabled //테스트 대상에서 제외할때
	void joinMember() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test2@test.com");
		memberVO.setPw("1234");
		memberVO.setTel("010-1234-5678");
		memberVO.setNickname("관리자2");
		memberVO.setGender("남");
		memberVO.setRegion("부산");
		memberVO.setBirth(java.sql.Date.valueOf("2000-01-02"));

		int cnt = memberDAO.joinMember(memberVO);
		
		logger.info("cnt:"+cnt);
	}
	
	@Test
	@DisplayName("회원수정")
	@Disabled
	void modifyMember() {
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test2@test.com");
		memberVO.setPw("3333");
		memberVO.setTel("010-1234-5678");
		memberVO.setNickname("관리자4");
		memberVO.setGender("여");
		memberVO.setRegion("경기");
		memberVO.setBirth(java.sql.Date.valueOf("2000-01-03"));
		
		int cnt = memberDAO.modifyMember(memberVO);
		
		Assertions.assertEquals(1,cnt);
		logger.info("cnt:"+cnt);
	}
	
	@Test
	@DisplayName("회원전체조회")
	@Disabled
	void listAllMember() {
		
		List<MemberVO> list = memberDAO.listAllMember();
		
		for(MemberVO memberVO : list) {
			logger.info(memberVO.toString());
		}
	}
	
	@Test
	@DisplayName("회원조회(개인)")
//	@Disabled
	void listOneMember() {
		String id = "sang105kr@gmail.com";
		MemberVO memberVO = memberDAO.listOneMember(id);
		logger.info(memberVO.toString());
	}
	
	@Test
	@DisplayName("회원 탈퇴")
	@Disabled
	void outMember() {
		String id = "test2@test.com";
		String pw = "1234";
		int result = memberDAO.outMember(id, pw);
		Assertions.assertEquals(1, result);
	}
	
	@Test
	@DisplayName("로그인")
	@Disabled
	void login() {
		String id = "test@test.com";
		String pw = "4444";
		MemberVO memberVO = memberDAO.login(id, pw);
		logger.info(memberVO.toString());
	}	
	
	@Test
	@DisplayName("아이디 찾기")
	@Disabled
	void findID() {
		String tel = "010-2222-3333";
		//String birth = "2000-03-01";
		Date birth = java.sql.Date.valueOf("2000-03-01");		
		String id = memberDAO.findID(tel, birth);
		Assertions.assertEquals("test@test.com", id);
		
	}
	@Test
	@DisplayName("비밀번호 찾기")
	@Disabled
	void findPW() {
		String id 		= "test@test.com";
		String tel 		= "010-2222-3333";
		//String birth 	= "2000-03-01";
		Date birth = java.sql.Date.valueOf("2000-03-01");				
		String pw = memberDAO.findPW(id,tel,birth);
		Assertions.assertEquals("1234", pw);
		
	}
	
	@Test
	@DisplayName("비밀번호변경")
	@Disabled
	void changePW() {
		String id = "test@test.com";
		String postpw = "4444";
		String prepw = "4444";
		int result = memberDAO.changePW(id, prepw,postpw);
//		Assertions.assertEquals(1,result);
		
		Assertions.assertEquals(postpw,memberDAO.listOneMember(id).getPw());
	}
}
