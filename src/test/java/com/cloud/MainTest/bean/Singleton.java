package com.cloud.MainTest.bean;

public class Singleton {

    private static Singleton instance = null;

    private int v;
    private Singleton() {
        this.v = 3;
    }

    public static Singleton getInstance() {

        if (instance == null) { // 1. 第一次检查
            synchronized (Singleton.class) { // 2
                if (instance == null) { // 3. 第二次检查
                    instance = new Singleton(); // 4
                }
            }
        }
        if(instance.v == 0){
            System.err.println(Thread.currentThread().getName() + "::" + instance);
        }else{
            System.out.println(Thread.currentThread().getName() + "::" + instance);
            instance = null;
        }
        return instance;
    }

    @Override
    public String toString() {
        return "Singleton{" +
                "v=" + v +
                '}';
    }
}