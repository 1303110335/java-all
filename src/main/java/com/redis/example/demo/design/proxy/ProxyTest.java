/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.design.proxy;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author xuleyan
 * @version ProxyTest.java, v 0.1 2020-10-23 4:05 下午
 */
public class ProxyTest {
    public static void main(String[] args) {
//        testProxy();
        generatorSrc();


    }

    private static void testProxy() {
        TargetObject target = new TargetObject();
        // ProxyFactory 实现了 InvocationHandler接口，其中的 getInstanse() 方法利用 Proxy 类
        // 生成了target目标对象的代理对象，并返回；且ProxyFactory持有对target的引用，可以在
        // invoke() 中完成对 target 相应方法的调用，以及目标方法前置后置的增强处理
        ProxyFactory proxyFactory = new ProxyFactory();
        // 这个mi就是JDK的 Proxy 类动态生成的代理类 $Proxy0 的实例，该实例中的方法都持有对
        // invoke() 方法的回调，所以当调用其方法时，就能够执行 invoke() 中的增强处理
        MyInterface mi = (MyInterface)proxyFactory.getInstanse(target);
        // 这样可以看到 mi 的 Class 到底是什么
        System.out.println(mi.getClass());
        // 这里实际上调用的就是 $Proxy0代理类中对 play() 方法的实现，结合下面的代码可以看到
        // play() 方法通过 super.h.invoke() 完成了对 InvocationHandler对象(proxyFactory)中
        // invoke()方法的回调，所以我们才能够通过 invoke() 方法实现对 target 对象方法的
        // 前置后置增强处理
        mi.play();
        // 总的来说，就是在invoke()方法中完成target目标方法的调用，及前置后置增强，
        // JDK动态生成的代理类中对 invoke() 方法进行了回调
    }

    /**
     * 将ProxyGenerator生成的动态代理类的输出到文件中，利用反编译工具luyten等就可
     * 以看到生成的代理类的源码咯，下面给出了其反编译好的代码实现
     */
    public static void generatorSrc(){
        byte[] bytesFile = ProxyGenerator.generateProxyClass("$Proxy0", TargetObject.class.getInterfaces());
        FileOutputStream fos = null;
        try{
            String path = System.getProperty("user.dir") + "/$Proxy0.class";
            System.out.println(path);
            File file = new File(path);
            fos = new FileOutputStream(file);
            fos.write(bytesFile);
            fos.flush();
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            try {
                fos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}