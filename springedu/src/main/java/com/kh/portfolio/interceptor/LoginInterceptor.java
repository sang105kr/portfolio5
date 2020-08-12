package com.kh.portfolio.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.kh.portfolio.member.vo.MemberVO;

public class LoginInterceptor implements HandlerInterceptor {
	
	private static final Logger logger 
		= LoggerFactory.getLogger(LoginInterceptor.class);
	
	//컨트롤서 수행전
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info("LoginInterceptor.preHandle");
		//요청URL분석
		String uri					= request.getRequestURI();
		String contextPath	= request.getContextPath();
		String reqURI				= uri.substring(contextPath.length());
		
		logger.info("요청uri="+reqURI);
		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("member");
		if(memberVO == null || memberVO.getId().isEmpty()) {
			logger.info("권한없는자의 접근시도가 있음"+request.getRemoteAddr());
			response.sendRedirect(request.getContextPath()+"/loginForm?reqURI="+reqURI);
			return false;
		}
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("LoginInterceptor.postHandle");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("LoginInterceptor.afterCompletion");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
