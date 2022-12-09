package net.ict.bodymanager.config;

import net.ict.bodymanager.handler.StompHandshakeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocket implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        // EndPoint : 서버와 클라이언트가 WebSocket 통신을 하기 위한 엔드포인트
        // 클라이언트측에서 socket을 생성할 때  여기에 정의한 문자열로 생성해야 통신

        // socketJs 클라이언트가 WebSocket 핸드셰이크를 하기 위해 연결할 endpoint를 지정할 수 있다.
        registry.addEndpoint("/chat/inbox")
                .setAllowedOriginPatterns("*") // cors 허용을 위해 꼭 설정해주어야 함. setCredential() 설정시에 AllowedOrigin 과 같이 사용될 경우 오류가 날 수 있으므로 OriginPatterns 설정으로 사용하였음
                .addInterceptors(new StompHandshakeInterceptor()) // 핸드쉐이크시 인터셉터를 넣을 수 있음
                .withSockJS()
                .setDisconnectDelay(30 * 1000);  // 클라이언트가 연결이 끊긴 것으로 간주되는 시간
//                .setClientLibraryUrl("https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.1/sockjs.min.js"); // 프론트에서 사용하는 sockjs 라이브러리와 동일하게 사용하였음
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 클라이언트로부터 메시지를 받을 api의 prefix를 설정함
        // publish
        registry.setApplicationDestinationPrefixes("/pub");
        /// setApplicationDestinationPrefixes 는 Client에서 SEND 요청을 처리한다. /pub 으로 보냄
        /// 메시지를 발행하는 요청의 prefix

        /// prefix(api 경로 맨 앞)에 붙은 경우, messageBroker가 잡아서 해당 채팅방을 구독하고 있는 클라이언트에게 메시지를 전달해줌

        // 메모리 기반 메시지 브로커가 해당 api를 구독하고 있는 클라이언트에게 메시지를 전달함
        // to subscriber
        registry.enableSimpleBroker("/sub");
        /// enableSimpleBroker 는 해당 경로로 SimpleBroker 를 등록한다 . SimpleBroker는 해당 경로를 구독하는 client에게 메시지를 전달한다.
        /// 메시지를 구독하는 요청의 prifix

    }




}
