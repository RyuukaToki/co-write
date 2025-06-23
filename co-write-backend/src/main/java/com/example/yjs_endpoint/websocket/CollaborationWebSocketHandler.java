//package com.example.yjs_endpoint.websocket;
//
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//import org.springframework.web.socket.*;
//import org.springframework.web.socket.handler.AbstractWebSocketHandler;
//import java.io.IOException;
//import java.nio.ByteBuffer;
//import java.util.*;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ConcurrentHashMap;
//
//public class CollaborationWebSocketHandler extends AbstractWebSocketHandler {
//    // 注入线程池
////    private final ThreadPoolTaskExecutor taskExecutor;
//
//    // 通过构造函数注入
////    public CollaborationWebSocketHandler(ThreadPoolTaskExecutor taskExecutor) {
////        this.taskExecutor = taskExecutor;
////    }
//    // 文档房间管理：docId -> 会话集合
//    private final Map<String, Set<WebSocketSession>> docRooms = new ConcurrentHashMap<>();
//
//    // 客户端心跳监测：sessionId -> 最后活跃时间
//    private final Map<String, Long> lastHeartbeat = new ConcurrentHashMap<>();
//    private static final long HEARTBEAT_TIMEOUT = 30000; // 30秒超时
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        String docId = getDocIdFromSession(session);
//
//        // 加入文档房间
//        docRooms.computeIfAbsent(docId, k -> ConcurrentHashMap.newKeySet())
//                .add(session);
//
//        // 初始化心跳
//        lastHeartbeat.put(session.getId(), System.currentTimeMillis());
//    }
//
//    @Override
//    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
//        String docId = getDocIdFromSession(session);
//        ByteBuffer buffer = message.getPayload();
//
//        // 心跳检测（Yjs可能发送空消息）
//        if (buffer.remaining() == 0) {
//            lastHeartbeat.put(session.getId(), System.currentTimeMillis());
//            return;
//        }
//
//        // 转发给同房间其他客户端
//        broadcast(docId, buffer, session);
//    }
//
//    private void broadcast(String docId, ByteBuffer message, WebSocketSession exclude) {
//        // 异步保存到数据库（不阻塞消息转发）
////        CompletableFuture.runAsync(() -> {
////            try {
////                // TODO
//////                saveDocumentUpdate(docId, message.array());
////            } catch (Exception e) {
//////                log.error("文档持久化失败", e);
////            }
////        }, taskExecutor); // 使用独立线程池
//
//        // 实时转发消息
//        docRooms.getOrDefault(docId, Collections.emptySet())
//                .stream()
//                .filter(session -> session.isOpen() && !session.equals(exclude))
//                .forEach(session -> {
//                    try {
//                        session.sendMessage(new BinaryMessage(message.slice()));
//                    } catch (IOException e) {
//                        cleanupSession(session);
//                    }
//                });
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
//        cleanupSession(session);
//    }
//
//    private void cleanupSession(WebSocketSession session) {
//        String docId = getDocIdFromSession(session);
//        if (docRooms.containsKey(docId)) {
//            docRooms.get(docId).remove(session);
//            if (docRooms.get(docId).isEmpty()) {
//                docRooms.remove(docId);
//            }
//        }
//        lastHeartbeat.remove(session.getId());
//    }
//
//    private String getDocIdFromSession(WebSocketSession session) {
//        // 从路径变量提取docId，如 /collab/doc_123
//        return session.getUri().getPath().split("/")[2];
//    }
//
//    // 定时清理死连接
//    @Scheduled(fixedRate = 60000)
//    public void checkHeartbeats() {
//        long now = System.currentTimeMillis();
//        lastHeartbeat.entrySet().removeIf(entry ->
//                (now - entry.getValue()) > HEARTBEAT_TIMEOUT
//        );
//    }
//}