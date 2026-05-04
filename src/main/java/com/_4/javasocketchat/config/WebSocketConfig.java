package com._4.javasocketchat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com._4.javasocketchat.service.JwtTokenService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private final JwtTokenService jwtTokenService;
    private final WebSocketHandshake handshake;

    public WebSocketConfig(JwtTokenService jwtTokenService, WebSocketHandshake handshake){
        this.jwtTokenService = jwtTokenService;
        this.handshake = handshake;
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        registry
                .addEndpoint("/ws/private")
                .setAllowedOriginPatterns("*")
                .setHandshakeHandler(handshake)
                .withSockJS()
                ;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setUserDestinationPrefix("/user")
                .setApplicationDestinationPrefixes("/ws")
                .enableSimpleBroker("/topic", "/queue");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    String token = jwtTokenService.getToken(accessor.getFirstNativeHeader("Authorization"));
                    log.info("Received token: " + token);
                    
                    Authentication authentication = jwtTokenService.authentication(token);
                    accessor.setUser(authentication);
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    log.info(
                        "STOMP CONNECT 认证成功: " + authentication.getName() + "\n" +
                        ">>> 收到 STOMP 帧: " + accessor.getCommand() +
                        " | sessionId=" + accessor.getSessionId() +
                        " | user=" + accessor.getUser()
                    );
                }
                return message;
            }
        });
    }
}
