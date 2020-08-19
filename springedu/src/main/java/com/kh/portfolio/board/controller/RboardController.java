package com.kh.portfolio.board.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.portfolio.board.svc.RboardSVC;

@RestController
@RequestMapping("/rboard")
public class RboardController {

	private static final Logger logger
	= LoggerFactory.getLogger(RboardController.class);
	
	@Inject
	RboardSVC rboardSVC;
	
	//댓글작성
	@PostMapping(value="", produces="application/json")
	public ResponseEntity<String> write(){
		ResponseEntity<String> res = null;
		
		return res;
	}
	//댓글수정
	
	//댓글삭제
	
	//댓글목록
}
