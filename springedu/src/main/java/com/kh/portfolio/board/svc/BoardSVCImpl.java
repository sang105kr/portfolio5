package com.kh.portfolio.board.svc;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.portfolio.board.dao.BoardDAO;
import com.kh.portfolio.board.vo.BoardFileVO;
import com.kh.portfolio.board.vo.BoardVO;

@Service
public class BoardSVCImpl implements BoardSVC {

	private static final Logger logger = LoggerFactory.getLogger(BoardSVCImpl.class);

	@Inject
	BoardDAO boardDAO;

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
	public int modify(BoardVO boardVO) {
		return 0;
	}

	// 게시글 삭제
	@Override
	public int delete(String bnum) {
		return 0;
	}

	// 게시글 보기
	@Override
	public BoardVO view(String bnum) {
		return null;
	}

	// 게시글 목록
	@Override
	public List<BoardVO> list() {
		return null;
	}

}
