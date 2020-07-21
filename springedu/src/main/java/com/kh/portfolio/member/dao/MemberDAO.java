package com.kh.portfolio.member.dao;

import java.util.List;

import com.kh.portfolio.member.vo.MemberVO;

public interface MemberDAO {
	//회원 등록
	int joinMember(MemberVO memberVO);
	//회원 수정
	int modifyMember(MemberVO memberVO);
	//회원 전체조회
	List<MemberVO> listAllMember();
	//회원 개별조회
	MemberVO listOneMember(String id);
	//회원 탈퇴
	int outMember(String id, String pw);
	//로그인
	MemberVO login(String id, String pw);
	//아이디 찾기
	String findID(String tel, String birth);
	//비밀번호 찾기
	String findPW(String id, String tel, String birth);
	//비밀번호 변경
	int changePW(String id, String pw);
	//프로파일 이미지 조회
	byte[] findProfileImg(String id);
}
