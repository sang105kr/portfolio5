package com.kh.portfolio.board.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.portfolio.board.svc.BoardSVC;
import com.kh.portfolio.board.vo.BoardCategoryVO;
import com.kh.portfolio.board.vo.BoardFileVO;
import com.kh.portfolio.board.vo.BoardVO;
import com.kh.portfolio.common.page.FindCriteria;
import com.kh.portfolio.common.page.RecordCriteria;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private static final Logger logger
		= LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	BoardSVC boardSVC;
		
	//현재 컨트롤러에서 만들어지는 view페이지내에서  boardCategoryVO 이름으로 참조가 가능하다
	@ModelAttribute("boardCategory") 
	public List<BoardCategoryVO> getCategory() {
		return boardSVC.getCategory();
	}
	
	//게시글 작성(화면)
	@GetMapping("/writeForm")
	public String writeForm(
			@ModelAttribute("boardVO") BoardVO boardVO, //case 1)  
			Model model) {
//case 2)		
//		model.addAttribute("boardVO", new BoardVO());
		
		return "/board/writeForm";
	}
	
	//게시글 작성처리
	@PostMapping("/write")
	public String write(
			@Valid @ModelAttribute("boardVO") BoardVO boardVO,
			BindingResult result
			//Model model
			) {

		if(result.hasErrors()) {
			//model.addAttribute("boardVO", boardVO);
			return "/board/writeForm";
		}
		
		boardSVC.write(boardVO);

		return "redirect:/board/list"; 
	}
	
	//게시글 목록
//	@GetMapping("/list")
//	public String list(Model model) {
//		
//		model.addAttribute("list", boardSVC.list());
//		
//		return "/board/list";
//	}
	
	//게시글 목록
	@GetMapping({"/list",
							 "/list/{reqPage}",
							 "/list/{reqPage}/{searchType}/{keyword}"})
	public String list(
			@PathVariable(value="reqPage",required = false) Optional<Integer> reqPage,
			@PathVariable(value="searchType",required = false) String searchType,
			@PathVariable(value="keyword",required = false) String keyword,
			Model model
			) {
		//url경로상에 reqPage값이 존재하지않으면 1로 설정함.
//		int page = 1;
//		if(reqPage.isPresent()) {
//			page = reqPage.get();
//		}
//		int page = (reqPage.isPresent()) ? reqPage.get() : 1;
//		
//		logger.info("reqPage:"+reqPage.orElse(1));
		
		model.addAttribute("list", boardSVC.list(reqPage.orElse(1),searchType,keyword));
		model.addAttribute("findCriteria", boardSVC.getFindCriteria(reqPage.orElse(1),searchType,keyword));

		return "/board/list";
	}
	
	//게시글 보기
	@GetMapping("/view/{bnum}")
	public String view(
			@PathVariable("bnum") String bnum,
			Model model) {
		
		Map<String,Object> map = boardSVC.view(bnum);
		
		BoardVO boardVO = (BoardVO)map.get("board");
		
		List<BoardFileVO> files = null;
		if(map.get("files") != null) {
			files = (List<BoardFileVO>)map.get("files");
		}
		
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("files", files);
		
		return "/board/readForm";
	}
	
	//게시글삭제
	@GetMapping("/delete/{bnum}")
	public String delete(@PathVariable("bnum") String bnum) {
		
		boardSVC.delete(bnum);
		
		return "redirect:/board/list";
	}
	
	//첨부파일 다운로드
	@GetMapping("/file/{fid}")
	public ResponseEntity<byte[]> getFile(@PathVariable("fid") String fid){
		ResponseEntity<byte[]> res = null;
		
		BoardFileVO boardFileVO = boardSVC.viewFile(fid);
		
		//응답 헤더에 mymeType과 파일사이즈 정보를 설정
		final HttpHeaders headers = new HttpHeaders();
		String[] mimeTypes = boardFileVO.getFtype().split("/");
		headers.setContentType(new MediaType(mimeTypes[0], mimeTypes[1]));
		headers.setContentLength(boardFileVO.getFsize());
		
		/* 첨부파일 명이 한글일경우 깨짐 방지 */
		String filename = null;
		try {
			filename =	new String(boardFileVO.getFname().getBytes("UTF-8"),"ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//응답 헤더에 첨부파일이 있음을 알려줌.
		headers.setContentDispositionFormData("attatchment", filename);
		
		res = new ResponseEntity<byte[]>(boardFileVO.getFdata(),headers,HttpStatus.OK);
		return res;
	}
	
	//게시글 수정
	@PostMapping("/modify")
	public String modify(
			@Valid @ModelAttribute("boardVO") BoardVO boardVO,
			BindingResult result) {
		//바인딩시 오류가 발생할경우
		if(result.hasErrors()) {
			return "/board/readForm";
		}
		
		//수정
		boardSVC.modify(boardVO);
		
		return "redirect:/board/view/"+boardVO.getBnum();
	}
	
	//게시글 첨부파일 개별 삭제
	@DeleteMapping("/file/{fid}")
	@ResponseBody
	public ResponseEntity<String> fileDelete(
			@PathVariable("fid") String fid
			){
		ResponseEntity<String> res = null;
		
		int result = boardSVC.deleteFile(fid);
		if(result == 1) {
			res = new ResponseEntity<String>("success",HttpStatus.OK);
		}else {
			res = new ResponseEntity<String>("file",HttpStatus.OK);
		}
		
		return res;
	}
	
	//게시글 답글작성(화면)
	@GetMapping("/reply/{bnum}")
	public String replyForm(
			@PathVariable("bnum") String bnum,
			Model model) {
		
		//부모글 가져오기
		Map<String,Object> map = boardSVC.view(bnum);
		BoardVO boardVO = (BoardVO)map.get("board");
		
		logger.info("reply:"+boardVO);
		
		boardVO.setBid("");
		boardVO.setBnickname("");
		boardVO.setBtitle("[답글] "+boardVO.getBtitle());
		boardVO.setBcontent("[원글] " + boardVO.getBcontent());
		
		model.addAttribute("boardVO", boardVO);
		return "/board/replyForm";
	}
	
	//게시글 답글작성
	@PostMapping("/reply")
	public String reply(
			@Valid @ModelAttribute BoardVO boardVO,
			BindingResult result) {
		
		logger.info("reply:"+boardVO);
		if(result.hasErrors()) {
			return "/board/replyForm";
		}
		
		boardSVC.reply(boardVO);
		
		return "redirect:/board/list";
	}
}




















