package com.kh.portfolio.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.kh.portfolio.board.vo.BoardCategoryVO;
import com.kh.portfolio.board.vo.BoardFileVO;
import com.kh.portfolio.board.vo.BoardVO;


@Repository
public class BoardDAOImplXML implements BoardDAO {
	
	private static final Logger logger =
			LoggerFactory.getLogger(BoardDAOImplXML.class);
	
	@Inject
	private SqlSession sqlSession;
	
	//게시판 카테고리 읽어오기
	@Override
	public List<BoardCategoryVO> getCategory() {
		List<BoardCategoryVO> list = null;
		list = sqlSession.selectList("mappers.BoardDAO-mapper.getCategory");
		return list;
	}
	
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
		int result = 0;
		result = sqlSession.update("mappers.BoardDAO-mapper.modify", boardVO);
		return result;
	}
	//게시글 삭제
	@Override
	public int delete(String bnum) {
		int result = 0;
		result = sqlSession.delete("mappers.BoardDAO-mapper.delete", Long.valueOf(bnum));
		return result;
	}
	//게시글 첨부파일 개별 삭제
	@Override
	public int deleteFile(String fid) {
		int result = 0;
		result = sqlSession.delete("mappers.BoardDAO-mapper.deleteFile", Long.valueOf(fid));
		return result;
	}
	//게시글 첨부파일 전체 삭제
	@Override
	public int deleteFiles(String bnum) {
		int result = 0;
		result = sqlSession.delete("mappers.BoardDAO-mapper.deleteFiles", Long.valueOf(bnum));
		return result;
	}	
	//게시글 보기
	@Override
	public BoardVO view(String bnum) {
		BoardVO boardVO = null;
		boardVO = sqlSession.selectOne("mappers.BoardDAO-mapper.view", Long.valueOf(bnum));
		return boardVO;
	}

	//게시글 목록
	@Override
	public List<BoardVO> list() {
		List<BoardVO> list = null;
		
		list = sqlSession.selectList("mappers.BoardDAO-mapper.list2");
		
		return list;
	}
	
	//게시글 목록
	@Override
	public List<BoardVO> list(int startRec, int endRec) {
		List<BoardVO> list = null;
		Map<String,Object> map = new HashMap<>();
		map.put("startRec", startRec);
		map.put("endRec", endRec);
		list = sqlSession.selectList("mappers.BoardDAO-mapper.list3",map);		
		return list;
	}
	//게시글 목록(검색포함)
	@Override
	public List<BoardVO> list(int startRec, int endRec, String searchType, String keyword) {
		List<BoardVO> list = null;
		Map<String,Object> map = new HashMap<>();
		map.put("startRec", startRec);
		map.put("endRec", endRec);
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		list = sqlSession.selectList("mappers.BoardDAO-mapper.list",map);		
		return list;
	}
	
	//파일첨부
	@Override
	public int addFile(BoardFileVO boardFileVO) {
		int result = 0;
		
		result = sqlSession.insert("mappers.BoardDAO-mapper.addFile", boardFileVO);
		
		return result;
	}
	//첨부파일조회	
	@Override
	public List<BoardFileVO> getFiles(String bnum) {
		List<BoardFileVO> list = null;
		list = sqlSession.selectList("mappers.BoardDAO-mapper.getFiles", Long.valueOf(bnum));
		return list;
	}
	//조회수 + 1 증가
	@Override
	public void updateBhit(String bnum) {
		
		sqlSession.update("mappers.BoardDAO-mapper.updateBhit", Long.valueOf(bnum));
	}

	//첨부파일 다운로드
	@Override
	public BoardFileVO viewFile(String fid) {
		BoardFileVO boardFileVO = null;
		boardFileVO = sqlSession.selectOne("mappers.BoardDAO-mapper.viewFile", Long.valueOf(fid));		
		return boardFileVO;
	}

	//게시글 답글
	@Override
	public int reply(BoardVO boardVO) {
		//1) 이전답글 step업데이트(최초원글 답글중 부모글보다 답글단계가 큰경우 +1증가)
		updateStep(boardVO.getBgroup(), boardVO.getBstep());
		
		//2) 답글달기
		return sqlSession.insert("mappers.BoardDAO-mapper.reply", boardVO);
	}

	//이전답글 step 업데이트
	private int updateStep(int bgroup, int bstep) {
		Map<String, Object> map = new HashMap<>();
		map.put("bgroup", bgroup);
		map.put("bstep", bstep);
		return sqlSession.update("mappers.BoardDAO-mapper.updateStep", map);
				
	}

	//게시글 총 레코드 수 
	@Override
	public int totalRecordCount() {
		return sqlSession.selectOne("mappers.BoardDAO-mapper.totalRecordCount");
	}
	@Override
	public int totalRecordCount(String searchType, String keyword) {
		Map<String, Object> map = new HashMap<>();
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		return sqlSession.selectOne("mappers.BoardDAO-mapper.searchedTotalRecordCount",map);
	}
}





