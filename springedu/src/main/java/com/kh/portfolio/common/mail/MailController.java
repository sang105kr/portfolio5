package com.kh.portfolio.common.mail;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.portfolio.common.util.PasswordGenerator;

@Controller
@EnableAsync
public class MailController {

	@Inject
	private MailService mailService;

	@GetMapping("/sendMail")
	public void sendMail(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// 수신자
		// String to = ((MemberVO)request.getSession().getAttribute("member")).getId();

		// 비밀번호 생성
		PasswordGenerator passwordGenerator 
			= new PasswordGenerator//
				.PasswordGeneratorBuilder().useDigits(true) // 숫자포함
				.useLower(true) 														// 소문자포함
				.useUpper(true) 														// 대문자포함
				.usePunctuation(true) 											// 특수문자포함
				.build();
		String password = passwordGenerator.generate(8); // 8자리 비밀번호 생성

		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append("<html><body>");
		sb.append("<meta http-equiv='Content-Type' content='text/html; charset=euc-kr'>");
		sb.append("<h1>" + "신규비밀번호" + "<h1><br>");
		sb.append("아래 비밀번호로 로그인 하셔서 비밀번호를 변경하세요.<br><br>");
		sb.append("<b>비밀번호 : </b>");
		sb.append(password + "<br><br>");
		sb.append("<a href='http://localhost:9080/portfolio/loginForm?id=sang105kr@gmail.com'>로그인</a>");
		sb.append("</body></html>");

//		mailService.sendMail(to, "신규 비밀번호 발송", sb.toString());
		mailService.sendMail("sang105kr2@naver.com", "신규 비밀번호 발송", sb.toString());
		out.print("메일을 발송하였습니다.!!");
	}

	@GetMapping("/sendSimpleMail")
	public void sendSimpleMail(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
    PrintWriter out = response.getWriter();	
		mailService.sendSimpleMail("테스트 메일입니다");
		out.print("메일을 발송하였습니다.!!");

	}
}
