//package com.example.yjs_endpoint.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//import java.util.concurrent.Executor;
//import java.util.concurrent.ThreadPoolExecutor;
//
//@Configuration
//public class ExecutorConfig {
//    @Bean("storageTaskExecutor")
//    public Executor taskExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(5);
//        executor.setMaxPoolSize(10);
//        executor.setKeepAliveSeconds(10);
//        executor.setQueueCapacity(1000);
//        executor.setThreadNamePrefix("doc-storage-");
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
//        executor.initialize();
//        return executor;
//    }
//}
