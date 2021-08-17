/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.config;

import com.alijk.bqcommon.search.conf.ElasticSearchConf;
import com.alijk.bqcommon.search.store.ElasticSearchStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 *
 * @author xuleyan
 * @version EsConfig.java, v 0.1 2021-05-11 8:30 下午
 */
@Configuration
@Slf4j
public class EsConfig {

    @Value("${es.cluster.address}")
    private String esAddr;
    @Value("${es.user.name}")
    private String userName;
    @Value("${es.password}")
    private String password;

    @Bean(value = {"elasticSearchStore", "esStore"})
    public ElasticSearchStore elasticSearchStore() {
        ElasticSearchStore elasticSearchStore = new ElasticSearchStore();
        ElasticSearchConf esConf = new ElasticSearchConf();
        esConf.setMultiThread(false);
        esConf.setScheme("http");
        esConf.setShards(5);
        esConf.setReplicas(0);
        /* 市一6个节点数 */
        esConf.setDataNodes(6);
        esConf.setConnectTimeout(5000);
        esConf.setSocketTimeout(60000);
        esConf.setConnectRequestTimeout(60000);
        esConf.setMaxTotalConnection(8);
        esConf.setIndexNames("test");
        esConf.setMultiThread(false);

        try {
            esConf.setAddress(esAddr);
            esConf.setUsername(userName);
            esConf.setPassword(password);
            elasticSearchStore.setEsConf(esConf);
            elasticSearchStore.init();
        } catch (Exception e) {
            log.error("es初始化失败", e);
        }
        return elasticSearchStore;
    }
}