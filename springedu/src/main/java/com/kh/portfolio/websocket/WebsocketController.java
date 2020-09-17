package com.kh.portfolio.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.portfolio.sse.SSEController;

@Controller
public class WebsocketController {
	private static final Logger logger = LoggerFactory.getLogger(SSEController.class);
		
	@GetMapping("/websocket/memorystatus")
	public String result() {
		return "/websocket/memorystatus";
	}
	
	@GetMapping(value = "/chat")
	public String getChatViewPage() {
		return "/chat/clientChat";
	}	
}
