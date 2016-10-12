package org.haozf.websocket;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

public class HelloHandler extends AbstractWebSocketHandler {

	private Map<String, WebSocketSession> clients = new ConcurrentHashMap<>();

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		// 接收到客户端消息时调用
		System.out.println("text message: " + session.getId() + "-" + message.getPayload());
		
		for(String key:clients.keySet()){
			WebSocketSession ws = clients.get(key);
			try {
				ws.sendMessage(new TextMessage(message.getPayload()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				clients.remove(key);
			}
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		if (!clients.containsKey(session.getId())) {
			clients.put(session.getId(), session);
		}

		// 与客户端完成连接后调用
		System.out.println("afterConnectionEstablished");
		System.out.println("getId:" + session.getId());
		session.sendMessage(new TextMessage("你好..."));

	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// 消息传输出错时调用
		System.out.println("handleTransportError");
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		// 一个客户端连接断开时关闭
		System.out.println("afterConnectionClosed");
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}
}