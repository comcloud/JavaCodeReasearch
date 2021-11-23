package com.cloud.MainTest.thread;

/**
 * @version v1.0
 * @ClassName NestedSynchronization
 * @Author rayss
 * @Datetime 2021/4/27 2:17 下午
 */

class MyRunnable implements Runnable{

     MyRunnable(){

    }
    @Override
    public void run() {
        MyRunnable.method1();
    }

    private static synchronized void method1(){
        System.out.println("method1");
        MyRunnable runnable = new MyRunnable();
        runnable.method2();
    }
    private synchronized void method2(){
        System.out.println("method2");
    }


}
public class NestedSynchronization {

    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable();
        new Thread(runnable,"thread-1").start();
        new Thread(runnable,"thread-2").start();
        new Thread(runnable,"thread-3").start();
    }
}
