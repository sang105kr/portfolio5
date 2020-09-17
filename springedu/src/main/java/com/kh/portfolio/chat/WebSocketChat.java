package com.kh.portfolio.chat;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ServerEndpoint(value = "/echo/{id}/{nickname}")
public class WebSocketChat {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketChat.class);
	private static final List<Session> sessionList = new ArrayList<Session>();

	public WebSocketChat() {
		logger.info("웹소켓(서버) 객체생성");
	}

	@OnOpen
	public void onOpen(Session session,
			@PathParam("id") String userId,
			@PathParam("nickname") String nickname) {

		logger.info("접속 세션 아이디:" + session.getId());
		try {
			final Basic basic = session.getBasicRemote();
			basic.sendText("접속되었습니다!");
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		sessionList.add(session);
		//모든 접속된 사용자에게 메시지를 전달한다.
		sendAllSessionToAlarm(session, nickname);		
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		logger.info(message.split(",")[0] + ": " + message.split(",")[1]);
		String[] msg = message.split(",");
		try {
			final Basic basic = session.getBasicRemote();
			basic.sendText(msg[0] + ":" + msg[1]);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		//모든 접속된 사용자에게 메시지를 전달한다.
		sendAllSessionToMessage(session, message);
	}

	@OnError
	public void onError(Throwable e, Session session) {

	}

	@OnClose
	public void onClose(Session session,
			@PathParam("id") String userId,
			@PathParam("nickname") String nickname			
			) {
		logger.info("세션 " + session.getId() + " 종료!!");
		sendAllSessionToClosed(session, nickname);

	}
	
	private void sendAllSessionToAlarm(Session self, String nickname) {
		try {
			for (Session session : WebSocketChat.sessionList) {
				if (!self.getId().equals(session.getId())) {
					session.getBasicRemote().sendText(nickname + "님이 입장하였습니다.");
				}
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}
	private void sendAllSessionToMessage(Session self, String message) {
		String[] msg = message.split(",");
		try {
			for (Session session : WebSocketChat.sessionList) {
				if (!self.getId().equals(session.getId())) {
					session.getBasicRemote().sendText(msg[0] + ":" + msg[1]);
				}
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}	
	
	private void sendAllSessionToClosed(Session self, String nickname) {
		try {
			for (Session session : WebSocketChat.sessionList) {
				if (!self.getId().equals(session.getId())) {
					session.getBasicRemote().sendText(nickname + "님이 퇴장하였습니다");
				}
			}
			sessionList.remove(self);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}	
}
