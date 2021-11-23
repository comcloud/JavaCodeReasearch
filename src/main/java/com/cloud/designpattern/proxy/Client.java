package com.cloud.designpattern.proxy;

/**
 * @version v1.0
 * @ClassName Client
 * @Author rayss
 * @Datetime 2021/6/20 4:12 下午
 */

public class Client {
    public static void main(String[] args) {
        CustomInvocationHandler handler = new CustomInvocationHandler(new SchoolService());
        Object proxyTarget = handler.getProxyTarget(() -> {
            System.out.println("前置增强");
        },()-> {
            System.out.println("后置增强");
        });
        System.out.println(proxyTarget.getClass().getName());
        if(proxyTarget instanceof Service){
            Service service = (Service) proxyTarget;
            service.service("成都");
        }
    }
}
