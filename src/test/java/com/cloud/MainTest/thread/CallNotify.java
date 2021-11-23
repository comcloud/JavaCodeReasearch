package com.cloud.MainTest.thread;

/**
 * @version v1.0
 * @ClassName CallNotify
 * @Author rayss
 * @Datetime 2021/4/26 11:21 下午
 */
class MyRunnable1 extends Thread{

    public void run(){
        synchronized (this) {
            System.out.println(Thread.currentThread().getName()+" started");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" has been notified");
        }
    }
}

class MyRunnable2 extends Thread{

    MyRunnable1 myRunnable1;

    MyRunnable2(MyRunnable1 MyRunnable1){
        this.myRunnable1=MyRunnable1;
    }
    public void run(){
        synchronized (this.myRunnable1) {
            System.out.println(Thread.currentThread().getName()+ " started");
            try {
                this.myRunnable1.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" has been notified");
        }
    }
}

class MyRunnable3 extends Thread{

    MyRunnable1 myRunnable1;
    MyRunnable3(MyRunnable1 MyRunnable1){
        this.myRunnable1=MyRunnable1;
    }
    public void run(){
        synchronized (this.myRunnable1) {
            System.out.println(Thread.currentThread().getName()+ " started");
            this.myRunnable1.notifyAll(); // Wakes up a single thread that is
            //waiting on this object's monitor.
            //If any threads are waiting on this object,
            //one of them is chosen to be awakened.
            //The choice is random and occurs at the
            //discretion of the implementation.

            //this.myRunnable1.notifyAll(); // Will wake up all threads
            //waiting on object's monitor.
            System.out.println(Thread.currentThread().getName()+
                    " has notified waiting threads");
        }
    }
}


/** Copyright (c), AnkitMittal JavaMadeSoEasy.com */
public class CallNotify {

    public static void main(String[] args) throws InterruptedException {

        MyRunnable1 myRunnable1=new MyRunnable1();
        MyRunnable2 myRunnable2=new MyRunnable2(myRunnable1);
        MyRunnable3 myRunnable3=new MyRunnable3(myRunnable1);

        Thread t1=new Thread(myRunnable1,"Thread-1");
        Thread t2=new Thread(myRunnable2,"Thread-2");
        Thread t3=new Thread(myRunnable3,"Thread-3");

        t1.start();
        t2.start();
        Thread.sleep(100);  //Used to ensure that thread1 and thread2 starts before thread-3
        //because thread-1 and 2 calls wait(), while thread-3 calls notify or notifyAll()
        t3.start();
    }
}

/* OUTPUT with notify()

Thread-1 started
Thread-2 started
Thread-3 started
Thread-3 has notified waiting threads
Thread-1 has been notified

*/

/* OUTPUT with notifyAll()

Thread-1 started
Thread-2 started
Thread-3 started
Thread-3 has notified waiting threads
Thread-1 has been notified
Thread-2 has been notified

*/
