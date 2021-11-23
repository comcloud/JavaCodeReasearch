package com.cloud.MainTest.thread;

import java.util.Date;

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
