package com.github.thofis.tscg.stockvalues;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class StocksHandler extends TextWebSocketHandler {

	private static final Logger log = LoggerFactory.getLogger(StocksHandler.class);

	private List<WebSocketSession> sessions = new ArrayList<>();

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws InterruptedException, IOException {
		for (WebSocketSession webSocketSession : sessions) {
			Map value = new Gson().fromJson(message.getPayload(), Map.class);
			String[] data = value.get("data").toString().split(",");
			webSocketSession.sendMessage(new TextMessage("Dear " + data[0] + ", you complaint is now ..."));
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.debug("new session added. id = {}", session.getId());
		sessions.add(session);
	}

	public List<WebSocketSession> getSessions() {
		return sessions;
	}
}