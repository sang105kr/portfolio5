package com.kh.portfolio.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cal")
public class CalculatorController {
	private static Logger logger =
			LoggerFactory.getLogger(CalculatorController.class);
	
	
	@GetMapping("/view")
	public String calView() {
		
		return "result";
	}
	@GetMapping("/result")
	public String calResult(
			@RequestParam("op1") int op1,
			@RequestParam("op2") int op2,
			Model model
			) {
		
		model.addAttribute("result", op1+op2);
		
		return "result";
	}
}
