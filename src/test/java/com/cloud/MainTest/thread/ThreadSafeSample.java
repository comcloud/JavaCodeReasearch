package com.cloud.MainTest.thread;

public class ThreadSafeSample {
    public int sharedState;

    public static void main(String[] args) throws InterruptedException {
        ThreadSafeSample sample = new ThreadSafeSample();
        Thread threadA = new Thread(sample::nonSafeAction);
        Thread threadB = new Thread(sample::nonSafeAction);
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
    }

    public void nonSafeAction() {
        while (sharedState < 100000) {
            int former = sharedState++;
            int latter = sharedState;
            if (former != latter - 1) {
                System.out.println("Observed data race, former is " + former + ", " + "latter is " + latter);
            }
        }
    }
}