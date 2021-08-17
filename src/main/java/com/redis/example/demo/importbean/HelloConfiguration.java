/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.importbean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author xuleyan
 * @version HelloConfiguration.java, v 0.1 2021-04-12 10:35 上午
 */
@Configuration
@ComponentScan("com.redis.example.demo.importbean")
@Import(HelloImportBeanDefinitionRegistrar.class)
public class HelloConfiguration {

}