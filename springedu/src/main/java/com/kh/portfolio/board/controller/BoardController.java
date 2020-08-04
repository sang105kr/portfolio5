package com.kh.portfolio.board.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.portfolio.board.svc.BoardSVC;
import com.kh.portfolio.board.vo.BoardVO;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private static final Logger logger
		= LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	BoardSVC boardSVC;
	
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
			@Valid BoardVO boardVO,
			BindingResult result ) {

		if(result.hasErrors()) {
			return "/board/writeForm";
		}
		
		boardSVC.write(boardVO);

		return "/board/list"; 
	}
	
	//게시글 목록
	@GetMapping("/list")
	public String list(Model model) {
		
		model.addAttribute("list", boardSVC.list());
		
		return "/board/list";
	}
	
	//게시글 보기
	@GetMapping("/view/{bnum}")
	public String view(
			@PathVariable("bnum") String bnum,
			Model model) {
		
		model.addAttribute("boardVO", boardSVC.view(bnum));
		
		return "/board/readForm";
	}
}






