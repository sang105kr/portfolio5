package com.kh.portfolio.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.portfolio.common.PasswordGeneratorSVC;
import com.kh.portfolio.common.mail.MailService;
import com.kh.portfolio.common.util.PasswordGenerator;
import com.kh.portfolio.member.svc.MemberSVC;
import com.kh.portfolio.member.vo.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Inject
	MemberSVC memberSVC;

	@Inject
	PasswordGeneratorSVC passwordGeneratorSVC;

	@Inject
	MailService mailService;

	// 회원가입 화면
	@GetMapping("/joinForm")
	public String joinForm() {

		return "/member/joinForm";
	}

	// 회원가입처리
	@PostMapping("/join")
	public String join(MemberVO memberVO, Model model) {
		logger.info("MemberController.join(MemberVO memberVO)호출됨!");
		logger.info(memberVO.toString());

		int result = memberSVC.joinMember(memberVO);
		if (result == 1) {
			return "/member/loginForm";
		} else {
			model.addAttribute("svr_msg", "error");
			return "/member/joinForm";
		}
	}

	// 내정보
	@GetMapping("/myPage")
	public String mypage() {

		return "/member/myPage";
	}

	// 회원정보수정 화면
	@GetMapping("/modifyForm")
	public String modifyForm() {

		return "/member/modifyForm";
	}

	// 회원정보수정 처리
	@PostMapping("/modify")
	public String modify(MemberVO memberVO, Model model, HttpSession session) {

		int result = memberSVC.modifyMember(memberVO);
		// 회원수정 실패
		if (result != 1) {
			model.addAttribute("svr_msg", "비밀번호가 일치하지 않습니다.");
			return "/member/modifyForm";
		}

		// 세션에서 id정보를 가져온다.
		String id = ((MemberVO) session.getAttribute("member")).getId();

		// 수정된 회원 정보를 다시 읽어온다.
		session.setAttribute("member", memberSVC.listOneMember(id));

		return "redirect:/member/modifyForm";
	}

	// 비밀번호 변경 화면
	@GetMapping("/chagePWForm")
	public String changePWForm() {

		return "/member/chagePWForm";
	}

	// 비밀번호 변경 처리
	@PostMapping("/chagePW")
	public String changePW(@RequestParam("id") String id, @RequestParam("prepw") String prepw,
			@RequestParam("postpw") String postpw, Model model) {

		int result = memberSVC.changePW(id, prepw, postpw);

		// 비밀번호가 일치하지 않을경우
		if (result != 1) {
			model.addAttribute("svr_msg", "비밀번호가 일치하지 않습니다.");
			return "/member/chagePWForm";
		}

		return "redirect:/member/myPage";
	}

	// 회원탈퇴 화면
	@GetMapping("outMemberForm")
	public String outMemberForm() {

		return "/member/outMemberForm";
	}

	// 회원탈퇴 처리
	@PostMapping("outMember")
	public String outMember(@RequestParam("id") String id, @RequestParam("pw") String pw, HttpSession session,
			Model model) {

		// 1)db에서 회원정보 삭제
		int result = memberSVC.outMember(id, pw);
		if (result != 1) {
			model.addAttribute("svr_msg", "비밀번호가 일치하지 않습니다.");
			return "/member/outMemberForm";
		} else {
			// 2) 세션정보 제거
			session.invalidate();
		}

		return "redirect:/";
	}

	// 아이디 찾기 화면
	@GetMapping("findIDForm")
	public String findIDForm() {

		return "/member/findIDForm";
	}

	// 아이디 찾기(Restfull 처리, 응답포맷:JSON)
	@PostMapping(value = "/id", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Map> findID(@RequestBody MemberVO memberVO) {
		logger.info("ResponseEntity<Map> findID() 호출됨!!");
		logger.info("tel:" + memberVO.getTel());
		logger.info("birth:" + memberVO.getBirth());
		ResponseEntity<Map> res = null;
		String findID = null;

		// 문자열 birth를 java.sql.Date타입으로 변환
		memberVO.setBirth(java.sql.Date.valueOf(memberVO.getBirth().toString()));
		findID = memberSVC.findID(memberVO.getTel(), memberVO.getBirth());

		Map<String, Object> map = new HashMap<>();
		// 아이디를 찾았으면
		if (findID != null) {
			map.put("rtcode", "00");
			map.put("result", findID);
			res = new ResponseEntity<Map>(map, HttpStatus.OK); // 200
		} else {
			map.put("rtcode", "01");
			map.put("result", "찾고자 하는 아이디가 없습니다.");
			res = new ResponseEntity<Map>(map, HttpStatus.OK); // 200
		}

		return res;
	}

	// 비밀번호 찾기 화면
	@GetMapping("findPWForm")
	public String findPWForm() {

		return "/member/findPWForm";
	}

	// 비밀번호 찾기(Restfull 처리, 응답포맷:JSON)
	@PostMapping(value = "/pw", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Map> findPW(@RequestBody MemberVO memberVO) {
		logger.info("ResponseEntity<Map> findPW() 호출됨!!");
		logger.info("tel:" + memberVO.getId());
		logger.info("tel:" + memberVO.getTel());
		logger.info("birth:" + memberVO.getBirth());
		ResponseEntity<Map> res = null;
		String findPW = null;

		// 문자열 birth를 java.sql.Date타입으로 변환
		memberVO.setBirth(java.sql.Date.valueOf(memberVO.getBirth().toString()));
		findPW = memberSVC.findPW(memberVO.getId(), memberVO.getTel(), memberVO.getBirth());

		Map<String, Object> map = new HashMap<>();
		// 비밀번호를 찾았으면
		if (findPW != null) {
			map.put("rtcode", "00");
			map.put("result", findPW);
			res = new ResponseEntity<Map>(map, HttpStatus.OK); // 200
		} else {
			map.put("rtcode", "01");
			map.put("result", "비밀번호를 찾을 수  없습니다.");
			res = new ResponseEntity<Map>(map, HttpStatus.OK); // 200
		}

		return res;
	}

	// 비밀번호 찾기(Restfull 처리, 응답포맷:JSON)
	@PostMapping(value = "/pwmail", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Map> findPWMail(@RequestBody MemberVO memberVO, HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("ResponseEntity<Map> findPW() 호출됨!!");
		logger.info("id:" + memberVO.getId());
		logger.info("tel:" + memberVO.getTel());
		logger.info("birth:" + memberVO.getBirth());
		ResponseEntity<Map> res = null;
		String findPW = null;

		// 문자열 birth를 java.sql.Date타입으로 변환
		memberVO.setBirth(java.sql.Date.valueOf(memberVO.getBirth().toString()));
		findPW = memberSVC.findPW(memberVO.getId(), memberVO.getTel(), memberVO.getBirth());

		Map<String, Object> map = new HashMap<>();
		// 1)비밀번호를 찾았으면
		if (findPW != null) {
			// 2)임시비번호 생성
			String tmpPassword = passwordGeneratorSVC.geneterPassword(6);

			// 3)임시밀번호로 비밀번호변경
			memberSVC.changePW(memberVO.getId(), findPW, tmpPassword);

			// 4)메일전송

			String mailTitle = "신규 비밀번호 발송";

			// 로긴주소
			StringBuffer url = new StringBuffer();
			url.append("http://" + request.getServerName());
			url.append(":" + request.getServerPort());
			url.append(request.getContextPath());
			url.append("/loginForm?id=" + memberVO.getId());

			// 메일본문내용
			StringBuffer sb = new StringBuffer();
			sb.append("<html><body>");
			sb.append("<meta http-equiv='Content-Type' content='text/html; charset=euc-kr'>");
			sb.append("<h1>" + "신규비밀번호" + "<h1><br>");
			sb.append("아래 비밀번호로 로그인 하셔서 비밀번호를 변경하세요.<br><br>");
			sb.append("<b>비밀번호 : </b>");
			sb.append(tmpPassword + "<br><br>");
			sb.append("<a href='" + url.toString() + "'>로그인</a>");
			sb.append("</body></html>");

			mailService.sendMail(memberVO.getId(), mailTitle, sb.toString());
			logger.info("메일을 발송하였습니다.!!");

			map.put("rtcode", "00");
			map.put("result", "임시비밀번호가 메일로 전송되었습니다");
			res = new ResponseEntity<Map>(map, HttpStatus.OK); // 200
			
		} else {
			
			map.put("rtcode", "01");
			map.put("result", "비밀번호를 찾을 수  없습니다.");
			res = new ResponseEntity<Map>(map, HttpStatus.OK); // 200
		}

		return res;
	}
}
