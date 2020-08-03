package com.kh.portfolio.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.kh.portfolio.board.vo.BoardFileVO;
import com.kh.portfolio.board.vo.BoardVO;


@Repository
public class BoardDAOImplXML implements BoardDAO {
	
	private static final Logger logger =
			LoggerFactory.getLogger(BoardDAOImplXML.class);
	
	@Inject
	private SqlSession sqlSession;
	
	//게시글 작성
	@Override
	public int write(BoardVO boardVO) {
		int result = 0;
		
		result = sqlSession.insert("mappers.BoardDAO-mapper.write", boardVO);
		
		return result;
	}
	//게시글 수정
	@Override
	public int modify(BoardVO boardVO) {
		return 0;
	}
	//게시글 삭제
	@Override
	public int delete(String bnum) {
		return 0;
	}
	//게시글 보기
	@Override
	public BoardVO view(String bnum) {
		return null;
	}
	//게시글 목록
	@Override
	public List<BoardVO> list() {
		List<BoardVO> list = null;
		
		list = sqlSession.selectList("mappers.BoardDAO-mapper.list");
		
		return list;
	}
	//파일첨부
	@Override
	public int addFile(BoardFileVO boardFileVO) {
		int result = 0;
		
		result = sqlSession.insert("mappers.BoardDAO-mapper.addFile", boardFileVO);
		
		return result;
	}

}





