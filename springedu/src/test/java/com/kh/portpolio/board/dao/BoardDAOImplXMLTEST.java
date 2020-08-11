package com.kh.portpolio.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kh.portfolio.board.dao.BoardDAO;
import com.kh.portfolio.board.vo.BoardCategoryVO;
import com.kh.portfolio.board.vo.BoardFileVO;
import com.kh.portfolio.board.vo.BoardVO;
import com.kh.portfolio.common.page.RecordCriteria;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class BoardDAOImplXMLTEST {

	private final static Logger logger
	= LoggerFactory.getLogger(BoardDAOImplXMLTEST.class);

	@Inject
	BoardDAO boardDAO;
	
	@Inject
	RecordCriteria recordCriteria;
	
	@Test
	@DisplayName("게시판 카테고리 읽어오기")
	@Disabled
	void getCategory() {
		List<BoardCategoryVO> list = boardDAO.getCategory();
		//case1) 일반for문 
		for(int i=0; i<list.size(); i++) {
			logger.info(list.get(i).toString());
		}
		//case2) 향상된for문
		for(BoardCategoryVO boardCategoryVO: list) {
			logger.info(boardCategoryVO.toString());
		}
		//case3) 스트림사용
		list.stream().forEach(boardCategoryVO->{
			System.out.println(boardCategoryVO);
		});
		//case4) 스트림사용(메소드참드)
		list.stream().forEach(System.out::println);
	}
	
	@Test
	@DisplayName("게시글 작성")
	@Disabled
	void write() {
//    #{cid},
//    #{btitle},
//    #{id},
//    #{nickname},
//    #{bcontent},		
		BoardVO boardVO = new BoardVO();
		BoardCategoryVO boardCategoryVO = new BoardCategoryVO();
		
		boardVO.setBoardCategoryVO(boardCategoryVO);
		boardVO.getBoardCategoryVO().setCid(1001);
		boardVO.setBtitle("테스트 제목");
		boardVO.setBid("sang105kr@gmail.com");
		boardVO.setBnickname("별칭1");
		boardVO.setBcontent("본문1");
		
		int result = boardDAO.write(boardVO);
		Assertions.assertEquals(1, result);
	}
	
	@Test
	@DisplayName("게시글목록")
	@Disabled
	void list2() {
		
		List<BoardVO> list = boardDAO.list();
		logger.info("레코드갯수:" + list.size());
		
//		list.stream().forEach((board)->{
//			System.out.println(board);
//		});
		list.stream().forEach(System.out::println);
		
//		logger.info("게시글 목록:" + list.toString());
	}
	@Test
	@DisplayName("게시글목록")
	@Disabled
	void list() {
		
		recordCriteria.setReqPage(1);  			//요청페이지
		recordCriteria.setRecNumPerPage(10);		//한페이지에 보여줄 레코드 수
		
		List<BoardVO> list = boardDAO.list(recordCriteria.getStarRec(),
																			 recordCriteria.getEndRec());
		logger.info("레코드갯수:" + list.size());
		
//		list.stream().forEach((board)->{
//			System.out.println(board);
//		});
		list.stream().forEach(System.out::println);
		
//		logger.info("게시글 목록:" + list.toString());
	}	
	@Test
	@DisplayName("게시글 보기")
	@Disabled
	void view() {
		String bnum = "66";
		
		BoardVO boardVO = boardDAO.view(bnum);
		logger.info(boardVO.toString());
	}
	@Test
	@DisplayName("첨부파일조회")
	@Disabled
	void getFiles() {
		String bnum = "66";
		List<BoardFileVO> list = boardDAO.getFiles(bnum);
		
		list.stream().forEach(System.out::println);
		Assertions.assertEquals(3, list.size());
		
	}
	@Test
	@DisplayName("조회수+1증가")
	@Disabled
	void updateBhit() {
		String bnum = "66";
		int preBhit = boardDAO.view(bnum).getBhit();
		boardDAO.updateBhit(bnum);
		int postBhit = boardDAO.view(bnum).getBhit();
		Assertions.assertEquals(preBhit+1, postBhit);
	}
	
	@Test
	@DisplayName("게시글삭제")
	@Disabled
	void delete() {
		String bnum = "69";
		int result = boardDAO.delete(bnum);
		Assertions.assertEquals(1, 1);
	}
	
	@Test
	@DisplayName("게시글 첨부파일 개별삭제")
	@Disabled
	void deleteFile() {
		String fid = "21";
		int result = boardDAO.deleteFile(fid);
		Assertions.assertEquals(1, 1);
	}
	
	@Test
	@DisplayName("첨부파일 다운로드")
	@Disabled
	void viewFile() {
		String fid = "19";
		BoardFileVO boardFileVO = boardDAO.viewFile(fid);
		logger.info(boardFileVO.toString());
		
	}
	
	
//	INSERT INTO board (
//	    bnum,
//	    bcategory,
//	    btitle,
//	    bid,
//	    bnickname,
//	    bcontent,
//	    bgroup,
//	    bstep,
//	    bindent
//	) VALUES (
//	    BOARD_BNUM_SEQ.nextval,
//	    #{boardCategoryVO.cid},
//	    #{btitle},
//	    #{bid},
//	    #{bnickname},
//	    #{bcontent},
//	    BOARD_BNUM_SEQ.nextval,
//	    #{bstep} + 1, 
//	    #{bindent} + 1
//	)	
	@Test
	@DisplayName("게시글 답글")
	@Disabled
	void reply() {
		BoardVO boardVO = new BoardVO();
		BoardCategoryVO boardVoCategoryVO = new BoardCategoryVO();
		
		boardVO.setBnum(130);
		boardVO.setBoardCategoryVO(boardVoCategoryVO);
		boardVO.getBoardCategoryVO().setCid(1002);
		boardVO.setBtitle("[답글][답글]제목2");
		boardVO.setBgroup(66);
		boardVO.setBstep(1);
		boardVO.setBindent(1);
		
		int result = boardDAO.reply(boardVO);
		
	}
	
	@Test
	@DisplayName("샘플 게시글 작성")
	void writeSampleData() {
//    #{cid},
//    #{btitle},
//    #{id},
//    #{nickname},
//    #{bcontent},	
		for(int i=1; i < 325; i++) {
			BoardVO boardVO = new BoardVO();
			BoardCategoryVO boardCategoryVO = new BoardCategoryVO();
			
			boardVO.setBoardCategoryVO(boardCategoryVO);
			boardVO.getBoardCategoryVO().setCid(1001);
			boardVO.setBtitle("테스트 제목"+i);
			boardVO.setBid("sang105kr@gmail.com");
			boardVO.setBnickname("별칭"+i);
			boardVO.setBcontent("본문"+i);
			
			boardDAO.write(boardVO);
		}
	}
}












