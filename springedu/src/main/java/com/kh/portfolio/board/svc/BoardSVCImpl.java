package com.kh.portfolio.board.svc;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.portfolio.board.dao.BoardDAO;
import com.kh.portfolio.board.vo.BoardCategoryVO;
import com.kh.portfolio.board.vo.BoardFileVO;
import com.kh.portfolio.board.vo.BoardVO;
import com.kh.portfolio.common.page.FindCriteria;
import com.kh.portfolio.common.page.PageCriteria;
import com.kh.portfolio.common.page.RecordCriteria;

@Service
public class BoardSVCImpl implements BoardSVC {

	private static final Logger logger = LoggerFactory.getLogger(BoardSVCImpl.class);

	@Inject
	BoardDAO boardDAO;
	
	@Inject
	RecordCriteria recordCriteria;
	
	@Inject
	PageCriteria pageCriteria;
		
	@Inject
	FindCriteria findCriteria;
	
	//게시판 카테고리 읽어오기
	@Override
	public List<BoardCategoryVO> getCategory() {		
		return boardDAO.getCategory();
	}
	
	// 게시글 작성
	@Transactional //트랜션 처리가 필요하다고 알려준다.
	@Override
	public int write(BoardVO boardVO) {
		int result = 0;

		// 1) 게시글 저장
		result = boardDAO.write(boardVO);

		// 2) 첨부파일 저장
		// 첨부파일이 존재하면
		List<MultipartFile> files = boardVO.getFiles();
		if (files != null && files.size() > 0) {

			addFiles(files, boardVO.getBnum());
		}

		return result;
	}

	// 첨부파일 저장
	private void addFiles(List<MultipartFile> files, long bnum) {

		BoardFileVO boardFileVO = null;
		for (MultipartFile file : files) {
			boardFileVO = new BoardFileVO();

			try {
				// 게시글번호
				boardFileVO.setBnum(bnum);
				// 파일명
				boardFileVO.setFname(file.getOriginalFilename());
				// 파일크기
				boardFileVO.setFsize(file.getSize());
				// 파일유형
				boardFileVO.setFtype(file.getContentType());
				// 첨부파일
				boardFileVO.setFdata(file.getBytes());
				// 첨부파일 저장
				if(boardFileVO.getFsize() > 0) {
					boardDAO.addFile(boardFileVO);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	// 게시글 수정
	@Override
	@Transactional
	public int modify(BoardVO boardVO) {
		int result = 0;
		
		//1) 게시글 수정
		result = boardDAO.modify(boardVO);
		
		// 2) 첨부파일 저장
		// 첨부파일이 존재하면
		List<MultipartFile> files = boardVO.getFiles();
		if (files != null && files.size() > 0) {

			addFiles(files, boardVO.getBnum());
		}
		
		return result;
	}

	// 게시글 삭제
	@Override
	public int delete(String bnum) {
		return boardDAO.delete(bnum);
	}
  //게시글 첨부파일 개별 삭제
	@Override
	public int deleteFile(String fid) {
		return boardDAO.deleteFile(fid);
	}	

	// 게시글 보기
	@Transactional
	@Override
	public Map<String,Object> view(String bnum) {
		BoardVO boardVO = null;
		List<BoardFileVO> files = null;
		
		//1) 게시글 가져오기
		boardVO = boardDAO.view(bnum);
		
		//2) 첨부파일 가져오기
		files = boardDAO.getFiles(bnum);
		
		//3) 조회수 + 1증가
		boardDAO.updateBhit(bnum);
				
		Map<String,Object> map = new HashMap<>();
		map.put("board", boardVO);
		if(files !=null && files.size() > 0) {
			map.put("files", files);
		}
		return map;
	}

	// 게시글 목록
	@Override
	public List<BoardVO> list() {
		List<BoardVO> list = null;
		
		list = boardDAO.list();
		
		return list;
	}
	// 게시글 목록
	@Override
	public List<BoardVO> list(int reqPage) {
		List<BoardVO> list = null;
		
		//요청페이지
		recordCriteria.setReqPage(reqPage);
		//한페이이지에보여줄 레코드수 셋팅
		recordCriteria.setRecNumPerPage(20);
		list = boardDAO.list(recordCriteria.getStarRec(),
												 recordCriteria.getEndRec());

		return list;
	}
	//게시글 목록(검색포함)
	@Override
	public List<BoardVO> list(int reqPage, String searchType, String keyword) {
		List<BoardVO> list = null;
		
		//요청페이지
		recordCriteria.setReqPage(reqPage);
		//한페이이지에보여줄 레코드수 셋팅
		recordCriteria.setRecNumPerPage(20);
		list = boardDAO.list(recordCriteria.getStarRec(),
												 recordCriteria.getEndRec(),
												 searchType,
												 keyword);

		return list;
	}
	
	// 첨부파일 다운로드
	@Override
	public BoardFileVO viewFile(String fid) {

		return boardDAO.viewFile(fid);
	}

	//게시글 답글
	@Transactional
	@Override
	public int reply(BoardVO boardVO) {
		int result = 0;

		// 1) 답글 저장
		result = boardDAO.reply(boardVO);

		// 2) 첨부파일 저장
		// 첨부파일이 존재하면
		List<MultipartFile> files = boardVO.getFiles();
		if (files != null && files.size() > 0) {

			addFiles(files, boardVO.getBnum());
		}

		return result;
	}

	//페이징제어 반환
	@Override
	public PageCriteria getPageCriteria(int reqPage) {

		//한페이지에 보여줄 레코드수
		recordCriteria.setRecNumPerPage(20);
		//사용자의 요청페이지
		recordCriteria.setReqPage(reqPage);
		//한페이지에보여줄 페이지수
		pageCriteria.setPageNumPerPage(10);
		//레코드정보
		pageCriteria.setRc(recordCriteria);
		//게시글 총 레코드 건수
		pageCriteria.setTotalRec(boardDAO.totalRecordCount());
		//페이징계산
		pageCriteria.calculatePaging();
		

		return pageCriteria;
	}

	//페이징제어 + 검색어포함
	@Override
	public FindCriteria getFindCriteria(int reqPage, String searchType, String keyword) {

		//한페이지에 보여줄 레코드수
		recordCriteria.setRecNumPerPage(20);
		//사용자의 요청페이지
		recordCriteria.setReqPage(reqPage);
		//한페이지에보여줄 페이지수
		pageCriteria.setPageNumPerPage(10);
		//레코드정보
		pageCriteria.setRc(recordCriteria);
		//게시글 총 레코드 건수
		pageCriteria.setTotalRec(boardDAO.totalRecordCount(searchType,keyword));
		//페이징계산
		pageCriteria.calculatePaging();
		//검색어정보
		findCriteria.setPageCriteria(pageCriteria);
		findCriteria.setSearchType(searchType);
		findCriteria.setKeyword(keyword);
		
		return findCriteria;
	}




}








