package com.kh.portfolio.member.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.kh.portfolio.member.vo.MemberVO;

@Repository
public class MemberDAOImplXML implements MemberDAO{

	private static final Logger logger
		= LoggerFactory.getLogger(MemberDAOImplXML.class);
	
	@Inject
	private SqlSession sqlSession;
	
	//회원 등록
	@Override
	public int joinMember(MemberVO memberVO) {
		logger.info("MemberDAOImplXML.joinMember(MemberVO memberVO) 호출됨");	
		return sqlSession.insert("mappers.MemberDAO-mapper.joinMember",memberVO);
	}
	//회원 수정
	@Override
	public int modifyMember(MemberVO memberVO) {
		logger.info("MemberDAOImplXML.modifyMember(MemberVO memberVO) 호출됨");	
		return sqlSession.update("mappers.MemberDAO-mapper.modifyMember", memberVO);
	}
	//회원 전체조회
	@Override
	public List<MemberVO> listAllMember() {
		logger.info("MemberDAOImplXML.listAllMember() 호출됨");
		return sqlSession.selectList("mappers.MemberDAO-mapper.listAllMember");
	}
	//회원 개별조회
	@Override
	public MemberVO listOneMember(String id) {
		logger.info("MemberDAOImplXML.listOneMember(String id) 호출됨");
		return sqlSession.selectOne("mappers.MemberDAO-mapper.listOneMember",id);
	}
	//회원 탈퇴
	@Override
	public int outMember(String id, String pw) {
		logger.info("MemberDAOImplXML.outMember(String id, String pw) 호출됨");
		Map<String,String> map = new HashMap<>();
		map.put("id",id);
		map.put("pw",pw);
		
		return sqlSession.delete("mappers.MemberDAO-mapper.outMember", map);
	}
	//로그인
	@Override
	public MemberVO login(String id, String pw) {
		logger.info("MemberDAOImplXML.login(String id, String pw) 호출됨");
		Map<String,String> map = new HashMap<>();
		map.put("id",id);
		map.put("pw",pw);
		return sqlSession.selectOne("mappers.MemberDAO-mapper.login", map);
	}
	//아이디 찾기
	@Override
	public String findID(String tel, String birth) {
		logger.info("MemberDAOImplXML.findID(String tel, String birth) 호출됨");
		Map<String,String> map = new HashMap<>();
		map.put("tel",tel);
		map.put("birth",birth);
		return sqlSession.selectOne("mappers.MemberDAO-mapper.findID", map);
	}
	//비밀번호 찾기
	@Override
	public String findPW(String id, String tel, String birth) {
		logger.info("MemberDAOImplXML.findPW(String id, String tel, String birth) 호출됨");
		Map<String,String> map = new HashMap<>();
		map.put("id",id);
		map.put("tel",tel);
		map.put("birth",birth);
		return sqlSession.selectOne("mappers.MemberDAO-mapper.findPW", map);
	}
	//비밀번호 변경
	@Override
	public int changePW(String id, String pw) {
		logger.info("MemberDAOImplXML.changePW(String id, String pw) 호출됨");
		Map<String,String> map = new HashMap<>();
		map.put("id",id);
		map.put("pw",pw);
		return sqlSession.update("mappers.MemberDAO-mapper.changePW", map);

	}
	//프로파일 이미지 조회
	@Override
	public byte[] findProfileImg(String id) {

		return null;
	}
	
	
	
}
