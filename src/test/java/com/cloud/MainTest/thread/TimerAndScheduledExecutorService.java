package com.cloud.MainTest.thread;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @version v1.0
 * @ClassName TimerAndScheduledExecutorService
 * @Author rayss
 * @Datetime 2021/7/20 9:47 上午
 */

public class TimerAndScheduledExecutorService {
    public static void main(String[] args) {
        TimerAndScheduledExecutorService service = new TimerAndScheduledExecutorService();
//        service.runTimer();
        service.runTimerByService();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        service.addOneTask();
        service.addOneTaskByService();
    }

    private final Timer timer = new Timer();

    //启动计时器
    public void runTimer() {
        timer.schedule(new TimerTask() {
            public void run() {
                throw new RuntimeException();
            }
        }, 1000 * 3, 500);
    }

    //向计时器添加一个任务
    public void addOneTask() {
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("hello world");
            }
        }, 1000, 1000 * 5);
    }

    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    public void runTimerByService() {
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            throw new RuntimeException();
        }, 1000 * 5, 1000 * 10, TimeUnit.MILLISECONDS);
    }

    public void addOneTaskByService() {
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            System.out.println("hello");
        }, 1000, 1000, TimeUnit.MILLISECONDS);
    }
}
