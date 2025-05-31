package com.proyectoFinal.Back.config;

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
        // Punto de conexión para los clientes WebSocket
        registry.addEndpoint("/ws-chat")
                .setAllowedOrigins("http://localhost:4200")
                .withSockJS(); // Fallback para navegadores sin soporte WebSocket
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Los mensajes que el servidor envía a los clientes usarán el prefijo /topic
        config.enableSimpleBroker("/topic");

        // Los mensajes que los clientes envían al servidor deben tener el prefijo /app
        config.setApplicationDestinationPrefixes("/app");
    }
}
