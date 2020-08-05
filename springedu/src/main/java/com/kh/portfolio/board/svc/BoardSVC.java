package com.kh.portfolio.board.svc;

import java.util.List;
import java.util.Map;

import com.kh.portfolio.board.vo.BoardCategoryVO;
import com.kh.portfolio.board.vo.BoardVO;

public interface BoardSVC {
	//게시판 카테고리 읽어오기
	List<BoardCategoryVO> getCategory();	
	//게시글 작성
	int write(BoardVO boardVO);	
	//게시글 수정
	int modify(BoardVO boardVO);	
	//게시글 삭제
	int delete(String bnum);
	//게시글 첨부파일 개별 삭제
	int deleteFile(String fid);	
	//게시글 보기
	Map<String, Object> view(String bnum);
	//게시글 목록
	List<BoardVO> list();
}
