///**
// * bianque.com
// * Copyright (C) 2013-2021 All Rights Reserved.
// */
//package com.redis.example.demo.nacos;
//
//import com.alibaba.nacos.api.annotation.NacosInjected;
//import com.alibaba.nacos.api.exception.NacosException;
//import com.alibaba.nacos.api.naming.NamingService;
//import com.alibaba.nacos.api.naming.pojo.Instance;
//import com.redis.example.demo.utils.ResponseWrapper;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.List;
//
//import static org.springframework.web.bind.annotation.RequestMethod.GET;
//
///**
// *
// * @author xuleyan
// * @version DiscoveryController.java, v 0.1 2021-04-19 8:57 下午
// */
//@Controller
//@RequestMapping("discovery")
//public class DiscoveryController {
//
//    @NacosInjected
//    private NamingService namingService;
//
//    @RequestMapping(value = "/get", method = GET)
//    @ResponseBody
//    public List<Instance> get(@RequestParam String serviceName) throws NacosException {
//        return namingService.getAllInstances(serviceName);
//    }
//
//    @RequestMapping(value = "/set", method = GET)
//    @ResponseBody
//    public ResponseWrapper<String> set(@RequestParam String serviceName) {
//        ResponseWrapper<String> response = new ResponseWrapper<>();
//        try {
//            namingService.registerInstance(serviceName, "127.0.0.1", 8848); // 注册中心的地址
//            response.setErrMsg("OK");
//            return response;
//        } catch (NacosException e) {
//            e.printStackTrace();
//            response.setErrMsg("ERROR");
//            response.setSuccess(false);
//            return response;
//        }
//    }
//}