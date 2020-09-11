package com.kh.portfolio.sse;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class SSEController {

	private static final Logger logger
	= LoggerFactory.getLogger(SSEController.class);
		
	@GetMapping("/sse/memorystatus")
	public String result() {
		return "/sse/memorystatus";
	}
	@GetMapping("/sse/memorymonitor")
	public SseEmitter streamSseMvc() {
			//content-Type : utf-8설정
	    Utf8SseEmitter emitter = new Utf8SseEmitter();
	    ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
	    sseMvcExecutor.execute(() -> {
	        try {
	        		ObjectMapper mapper = new ObjectMapper();
	        		Map<String,String> map =null;
	    				MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
	            for (int i = 0; true; i++) {
	  						long time = System.currentTimeMillis()+32400000; //+9시간
	  						int committed = (int)(memoryBean.getHeapMemoryUsage().getCommitted()/(1024 * 1024));
	  						int max = (int)(memoryBean.getHeapMemoryUsage().getMax()/(1024 * 1024));
	  						int used = (int)(memoryBean.getHeapMemoryUsage().getUsed()/(1024 * 1024));
	  						
	  						map = new HashMap<>();
	  						map.put("time",String.valueOf(time));
	  						map.put("max",String.valueOf(max));
	  						map.put("committed",String.valueOf(committed));
	  						map.put("used",String.valueOf(used));
	            	
	                SseEventBuilder event = SseEmitter.event()
	                	.name("message")
	                  .data(mapper.writeValueAsString(map))//java map객체=>json포맷문자열변환
	                  .id(String.valueOf(i));
	                emitter.send(event);
	                Thread.sleep(1000); //1초
	            }
	        } catch (Exception ex) {
	            emitter.completeWithError(ex);
	        }
	    });
	    return emitter;
	}
	private static final class Utf8SseEmitter extends SseEmitter {
	
	  private static final MediaType UTF8_TEXT_STREAM = new MediaType("text", "event-stream", Charset.forName("UTF-8"));
	
	  @Override
	  protected void extendResponse(ServerHttpResponse outputMessage) {
	      HttpHeaders headers = outputMessage.getHeaders();
	      if (headers.getContentType() == null) {
	          headers.setContentType(UTF8_TEXT_STREAM);
	      }
	  }
	}
}

