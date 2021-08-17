///**
// * bianque.com
// * Copyright (C) 2013-2020 All Rights Reserved.
// */
//package com.redis.example.demo.websocket;
//
//import org.springframework.context.EnvironmentAware;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.web.socket.server.standard.ServerEndpointExporter;
//
///**
// * @author xuleyan
// * @version WebSocketConfig.java, v 0.1 2020-12-21 5:47 下午
// */
//@Configuration
//public class WebSocketConfig implements EnvironmentAware {
//
//    /**
//     * 给spring容器注入这个ServerEndpointExporter对象
//     * 相当于xml：
//     * <beans>
//     * <bean id="serverEndpointExporter" class="org.springframework.web.socket.server.standard.ServerEndpointExporter"/>
//     * </beans>
//     * <p>
//     * 检测所有带有@serverEndpoint注解的bean并注册他们。
//     *
//     * @return
//     */
//    @Bean
//    public ServerEndpointExporter serverEndpointExporter() {
//        System.out.println("我被注入了");
//        return new ServerEndpointExporter();
//    }
//
//    @Override
//    public void setEnvironment(Environment environment) {
//        String redisHost = environment.getProperty("spring.redis.host");
//        System.out.println("redisHost = " + redisHost);
//    }
//}