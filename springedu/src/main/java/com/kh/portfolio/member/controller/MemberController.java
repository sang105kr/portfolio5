package com.kh.portfolio.member.controller;


import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.portfolio.member.svc.MemberSVC;
import com.kh.portfolio.member.vo.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberController {

	private static final Logger logger 
		= LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	MemberSVC memberSVC;
	
	//회원가입 화면
	@GetMapping("/joinForm")
	public String joinForm() {
		
		return "/member/joinForm";
	}
	
	//회원가입처리
	@PostMapping("/join")
	public String join(MemberVO memberVO, Model model) {
		logger.info("MemberController.join(MemberVO memberVO)호출됨!");
		logger.info(memberVO.toString());
		
		int result = memberSVC.joinMember(memberVO);
		if(result == 1) {
			return "/member/loginForm";			
		}else {
			model.addAttribute("errmsg", "error");
			return "/member/joinForm";
		}
		
	}
}








