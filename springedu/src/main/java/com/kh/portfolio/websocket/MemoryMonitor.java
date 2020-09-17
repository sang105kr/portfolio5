package com.kh.portfolio.websocket;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

@ServerEndpoint(value = "/websocket/memorymonitor")
public class MemoryMonitor {

	private static final Logger logger = LoggerFactory.getLogger(MemoryMonitor.class);

	public MemoryMonitor() {
		logger.info("웹소켓(서버) 객체생성");
	}

	@OnOpen
	public void onOpen(Session session) {
		logger.info("접속 세션 아이디:" + session.getId());
		try {
			final Basic basic = session.getBasicRemote();

			ExecutorService websocketExecutor = Executors.newSingleThreadExecutor();
			websocketExecutor.execute(() -> {

				ObjectMapper mapper = new ObjectMapper();
				Map<String, String> map = null;
				MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();

				while (true) {
					try {
						long time = System.currentTimeMillis() + 32400000;
						int committed = (int) (memoryBean.getHeapMemoryUsage().getCommitted() / (1024 * 1024));
						int max = (int) (memoryBean.getHeapMemoryUsage().getMax() / (1024 * 1024));
						int used = (int) (memoryBean.getHeapMemoryUsage().getUsed() / (1024 * 1024));

						map = new HashMap<>();
						map.put("time", String.valueOf(time));
						map.put("max", String.valueOf(max));
						map.put("committed", String.valueOf(committed));
						map.put("used", String.valueOf(used));

						basic.sendText(mapper.writeValueAsString(map));

						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
						break;
					}
				}
			});
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		try {
			final Basic basic = session.getBasicRemote();
			basic.sendText("to : " + message);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@OnError
	public void onError(Throwable e, Session session) {

	}

	@OnClose
	public void onClose(Session session) {
		logger.info("세션 " + session.getId() + " 종료!!");
	}
}
