package com.kh.portfolio.test;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/req")
public class RequestController {
	
	private static Logger logger =
		LoggerFactory.getLogger(RequestController.class);
	
	//1)HttpRequest객체이용
	@GetMapping("/1")
	public void req1(HttpServletRequest req) {
		logger.info("void req1() 호출됨!");
		
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		
		logger.info("name="	+ name);
		logger.info("age="	+ Integer.parseInt(age));
	}
	//2)@RequestParam 사용
	@GetMapping("/2")
	public void req2(
			@RequestParam("name") String rename, 
			@RequestParam("age") int reage) {
		logger.info("void req2() 호출됨!");
		
		
		logger.info("name="	+ rename);
		logger.info("age="	+ reage);
	}
	@GetMapping("/3")
	public void req3(
			@RequestParam Map<String,String> map) {
		logger.info("void req3() 호출됨!");
		
		logger.info("name="	+ map.get("name"));
		logger.info("age="	+ map.get("age"));
	}
	//3)사용자정의 클래스에 바인딩
	@GetMapping("/4")
	public void req4(Person person) {
		logger.info("void req4() 호출됨!");
		
		logger.info("name="	+ person.getName());
		logger.info("age="	+ person.getAge());
	}
	
	//4) 복수의 요청url에 대해 하나의 메소드에서 처리하고자할때
	@GetMapping(value={"/5","/6","/7"})
	//반환 타입이 void인경우 view이름: 요청URL에서 컨텍스루트 다음경로의 view이름을 찾는다.
	public void req5() {
		logger.info("void req5()호출됨");
	}
	
	//--서버응답 1.forward 2.redirect
	
	//1.forward 
	//  client요청횟수 : 1회
	//  request,response객체 : 공유(O).
	//  사용범위 			: 같은 웹어플리케이션내에서만 
	//-------------------------------------
	//2.redirect 
	//  client요청횟수 : 2회
	//  request,response객체 : 공유(X).
	//  사용범위 			: 모든범위(웹어프리케이션이 다르거나,외부서버요청포함) 
	//  **중요 : 클라이언트 요청이 서버의 변경을 요청하는경우 반드시 요청처리후 사용해줄것!
	@GetMapping("/8")
	public String req6(Model model) {
		
		model.addAttribute("key","value");
		return "req/7";  				//경로포함한 view이름 지정
//		return "forward:/req/7";  //forward요청시 '/'루트 지정해야함.
//		return "redirect:/req/7";
//			return "redirect:http://www.naver.com"; (O)
//			return "forward:http://www.naver.com"; //(X)
	}
	
	//컨트롤러 데이터를 리다이렉트 페이지에서 참조하고자할때 RedirectAttributes사용
	@GetMapping("/9")
	public String req7(Model model, RedirectAttributes redirectAttrs) {
		
		//model.addAttribute("key","value");
		redirectAttrs.addFlashAttribute("key", "value");
		return "redirect:/req/7";
	}
	
	//url경로명을 파라미터로 사용하고자할때(주로 Restfull 서비스 구현시 사용)
	@GetMapping("/10/{name}/{age}")
	public String req8(
			@PathVariable("name") String name,
			@PathVariable("age") String age,
			Model model
			) {
		
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		return "test";
	}
	//ModelAndView 사용
	@GetMapping("/11/{name}/{age}")
	public ModelAndView req9(
			@PathVariable("name") String name,
			@PathVariable("age") String age
			) {
		
		ModelAndView mav = new ModelAndView();
		
		//뷰정보
		mav.setViewName("test");
		//모델정보
		mav.addObject("name", name);
		mav.addObject("age", age);
		
		return mav;
	}
}








