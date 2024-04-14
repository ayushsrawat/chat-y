package com.spring.chatapp.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Register the "/chat" endpoint, enabling the SockJS protocol.
        // SockJS is used to enable fallback options for browsers that don’t support WebSocket.
        registry.addEndpoint("/chat").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Defines that the messages whose destination starts with "/app" should be routed to message-handling methods.
        registry.setApplicationDestinationPrefixes("/app");
        // Use this for enabling a simple message broker, which keeps track of subscriptions and broadcasts messages to connected users.
        registry.enableSimpleBroker("/topic");
    }
}
