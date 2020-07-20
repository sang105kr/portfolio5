package com.kh.portfolio.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/gugudan")
public class GugudanController {

	private static Logger logger =
			LoggerFactory.getLogger(GugudanController.class);
	
	
	//구구단 화면
	@GetMapping("/gugudanForm")
	public String gugudanForm() {
		logger.info("String gugudanForm() 호출됨!" );
		return "gugudanForm";
	}
	
	//구구단 처리
	@PostMapping("")
	public String gugudan(
			//요청파라이터 이름과 지역변수명이 같으면 매개값 생략가능
			//@RequestParam("dansu") String dansu 
			@RequestParam String dansu,
			Model model) {
		logger.info("String gugudan() 호출됨!:" + dansu );
		model.addAttribute("dansu", dansu);
		return "gugudanForm";
	}
	
}











