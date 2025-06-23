//package com.example.yjs_endpoint.config;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//import com.example.yjs_endpoint.websocket.CollaborationWebSocketHandler;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
//@Configuration
//@EnableWebSocket
//public class WebSocketConfig implements WebSocketConfigurer {
//
//    private final ThreadPoolTaskExecutor taskExecutor;
//
//    // 通过构造函数注入
//    public WebSocketConfig(ThreadPoolTaskExecutor taskExecutor) {
//        this.taskExecutor = taskExecutor;
//    }
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(collabWebSocketHandler(), "/collab/{docId}")
//                .setAllowedOrigins("*");
//    }
//
//    @Bean
//    public WebSocketHandler collabWebSocketHandler() {
//        return new CollaborationWebSocketHandler(taskExecutor);
//    }
//}