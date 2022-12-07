package net.ict.bodymanager.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Slf4j
@Component
//@Log4j2
public class StompHandshakeInterceptor implements HandshakeInterceptor {

    // 추가적으로 사용되며 handskake의 after, before 정보를 가로챌 수 있다
    // beforeHandshake() 함수는 무조건 retrun 값을 true로 해야 handshake가 이루어진다.

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        log.info("stomp handshake start");
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        log.info("stomp hadnshake success!");
    }
}
