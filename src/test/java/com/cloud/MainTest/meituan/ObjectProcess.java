package com.cloud.MainTest.meituan;

/**
 * @author 成都犀牛
 * @version 1.0
 * @datetime 2021/3/11 4:40 下午
 */
public class ObjectProcess {
    private ObjectProcess(){}

    private static volatile ObjectProcess instance;

    public static  ObjectProcess getInstance(){
        if(instance == null){
            synchronized (ObjectProcess.class){
                if(instance == null){
                    instance = new ObjectProcess();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Object o = new Object();

        for(int i = 0; i < 100; i++){
            new Thread(() -> System.out.println(ObjectProcess.getInstance().hashCode())).start();
        }
    }
}
