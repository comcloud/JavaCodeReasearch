package com.cloud.controller;

import java.util.Date;

/**
 * @author 成都犀牛
 * @version 1.0
 * @datetime 2021/2/24 11:07 上午
 */
public class CustomRunnable implements Runnable{

    private final String command;

    public CustomRunnable(String command){
        this.command = command;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Time = " + new Date());
        processCommand();
        System.out.println(Thread.currentThread().getName() + " End. Time = " + new Date());
    }

    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String toString() {
        return this.command;
    }
}
