package com.cloud.designpattern.proxy;

/**
 * @version v1.0
 * @ClassName SchoolService
 * @Author rayss
 * @Datetime 2021/6/20 5:03 下午
 */

public class SchoolService implements Service {
    @Override
    public void service(String schoolName) {
        System.out.println("学校服务:" + schoolName);
    }
}
