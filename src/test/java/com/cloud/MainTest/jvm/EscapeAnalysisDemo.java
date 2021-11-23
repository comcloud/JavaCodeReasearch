package com.cloud.MainTest.jvm;

import com.cloud.MainTest.bean.Person;

import java.util.concurrent.TimeUnit;

/**
 * -XX:+PrintGCDetails -Xmx256m -Xms256m -XX:+DoEscapeAnalysis
 * @version v1.0
 * @ClassName MethodAreaDemo
 * @Author rayss
 * @Datetime 2021/5/5 6:45 下午
 */

public class EscapeAnalysisDemo {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 10000000; i++) {
            allocation();
        }
        long end = System.currentTimeMillis();
        System.out.println("(end - start) = " + (end - start) + "ms");

        TimeUnit.MINUTES.sleep(10);
    }

    /**
     * 现在方法没有发生逃逸
     */
    private static void allocation(){
        Person person = new Person();
    }
}
