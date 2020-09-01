package com.kh.portfolio.member.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.portfolio.member.svc.MemberSVC;
import com.kh.portfolio.member.vo.MemberVO;

@Controller
public class LoginController {

	private static final Logger logger
		= LoggerFactory.getLogger(LoginController.class);
	
	@Inject
	MemberSVC memberSVC;
	
	//로그인 화면"/loginForm?reqURI="+reqURI
	@GetMapping("/loginForm")
	public String loginForm(
			@RequestParam(value="reqURI",required = false) String reqURI,
			Model model) {
		
		model.addAttribute("reqURI", reqURI);
		return "/member/loginForm";
	}
	
	//로그인 처리
	@PostMapping("/login")
	public String login(
			@RequestParam("id") String id,
			@RequestParam("pw") String pw,
			@RequestParam("reqURI") String reqURI,
			HttpSession session,
			Model model) {
//		logger.info("String login()호출됨");
//		logger.info("id : " + id);
//		logger.info("pw : " + pw);
		
		MemberVO memberVO = memberSVC.listOneMember(id);

		//1)회원id가 없는경우
		if(memberVO == null) {
			model.addAttribute("svr_msg", "가입된 회원 정보가 없습니다.");
			return "/member/loginForm";
		}else {
		//2)회원id가 존재할경우
			//2-1) 비밀번호가 일치하는경우
			if(memberVO.getPw().equals(pw)) {
				if(memberVO.getPic() != null) {
					byte[] encoded = Base64.encodeBase64(memberVO.getPic());
					memberVO.setPicBase64(new String(encoded));
					memberVO.setPic(null);
				}				
				session.setAttribute("member", memberVO);
			}else {
			//2-2) 비밀번호가 틀린경우
				model.addAttribute("svr_msg", "비밀번호가 일치하지 않습니다.");
				return "/member/loginForm";
			}
		}	
		return "redirect:/"+reqURI;
	}
	
	//로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		//세션에 저장된 정보 제거
		session.invalidate();
		return "redirect:/";
	}
	
}




