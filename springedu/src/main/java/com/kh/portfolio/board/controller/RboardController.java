package com.kh.portfolio.board.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.portfolio.board.svc.RboardSVC;
import com.kh.portfolio.board.vo.RboardVO;

@RestController
@RequestMapping("/rboard")
public class RboardController {

	private static final Logger logger
	= LoggerFactory.getLogger(RboardController.class);
	
	@Inject
	RboardSVC rboardSVC;
	
	//댓글작성
	@PostMapping(value="", produces="application/json")
	public ResponseEntity<String> write(
		@RequestBody RboardVO	rboardVO
			){
		ResponseEntity<String> res = null;
		
		int cnt = rboardSVC.write(rboardVO);
		
		//성공
		if(cnt==1) {	
			res = new ResponseEntity<String>("success",HttpStatus.OK); //200
		}else {
			res = new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST); //400
		}
		
		return res;
	}
	//댓글수정
	
	//댓글삭제
	
	//댓글목록
}
