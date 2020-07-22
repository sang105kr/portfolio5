package com.kh.portfolio.member.svc;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kh.portfolio.member.dao.MemberDAO;
import com.kh.portfolio.member.vo.MemberVO;

@Service
public class MemberSVCImpl implements MemberSVC{
	
	private static final Logger logger 
		= LoggerFactory.getLogger(MemberSVCImpl.class);
	
	@Inject
	@Qualifier("memberDAOImplXML")
	MemberDAO memberDAO;
	
	//회원 등록
	@Override
	public int joinMember(MemberVO memberVO) {
		int result = 0;
		result = memberDAO.joinMember(memberVO);
		return result;
	}
	
	//회원 수정
	@Override
	public int modifyMember(MemberVO memberVO) {

		return 0;
	}
	//회원 전체조회
	@Override
	public List<MemberVO> listAllMember() {

		return null;
	}
	//회원 개별조회
	@Override
	public MemberVO listOneMember(String id) {

		return null;
	}
	//회원 탈퇴
	@Override
	public int outMember(String id, String pw) {

		return 0;
	}
	//로그인
	@Override
	public MemberVO login(String id, String pw) {

		return null;
	}
	//아이디 찾기
	@Override
	public String findID(String tel, String birth) {

		return null;
	}
	//비밀번호 찾기
	@Override
	public String findPW(String id, String tel, String birth) {

		return null;
	}
	//비밀번호 변경
	@Override
	public int changePW(String id, String pw) {

		return 0;
	}
	//프로파일 이미지 조회
	@Override
	public byte[] findProfileImg(String id) {

		return null;
	}

}
