package com.cloud.MainTest.datastructure;

import lombok.extern.java.Log;

/**
 * @version v1.0
 * @ClassName ArrayQueue
 * @Author rayss
 * @Datetime 2021/5/8 4:57 下午
 */

@Log
public class ArrayQueueDemo {


    public static void main(String[] args) {
        CircleArrayQueue circleArrayQueue = new CircleArrayQueue(4);
        log.info("circleArrayQueue.isEmpty() = " + circleArrayQueue.isEmpty());
        circleArrayQueue.addQueue(1);
        log.info("circleArrayQueue.isFull() = " + circleArrayQueue.isFull());
        circleArrayQueue.addQueue(2);
        circleArrayQueue.addQueue(3);
        log.info("circleArrayQueue.getQueue() = " + circleArrayQueue.getQueue());
        circleArrayQueue.addQueue(4);
        circleArrayQueue.showQueue();
    }

    /**
     * 单向数组队列
     */
    private static class ArrayQueue {
        private int maxSize;
        //头部前一个指向下标
        private int front = -1;
        //尾部前一个指向下标
        private int rear = -1;
        private int[] data;

        public ArrayQueue(int maxSize) {
            this.maxSize = maxSize;
            data = new int[maxSize];
        }

        public boolean isFull() {
            return front == maxSize - 1;
        }

        public boolean isEmpty() {
            return front == rear;
        }

        public void addElement(int ele) {
            if (isFull()) {
                System.out.println("队列已经满了");
                return;
            }
            data[rear++] = ele;
        }

        public int getQueue() {
            if (isEmpty()) {
                throw new RuntimeException("队列为空");
            }
            return data[front++];
        }

        public void showQueue() {
            if (isEmpty()) {
                System.out.println("队列为空");
                return;
            }
            for (int i = 0; i < data.length; i++) {
                System.out.print(data[i] + "\t");
            }
        }

        public int headQueue() {
            if (isEmpty()) {
                throw new RuntimeException("队列为空");
            }
            return data[front + 1];
        }

    }

    /**
     * 数组循环队列
     * 注意这里一直会留出一个空间
     */
    private static class CircleArrayQueue {
        private int maxSize;
        //头部前一个指向下标
        private int front;
        //尾部前一个指向下标
        private int rear;
        private int[] data;

        public CircleArrayQueue(int maxSize) {
            this.maxSize = maxSize;
            data = new int[maxSize];
        }

        public boolean isFull() {
            //因为留出一个空间，所以判断是否为满的时候会先去对原有的队尾进行+1操作然后再取模判断
            return (rear + 1) % maxSize == front;
        }

        public boolean isEmpty() {
            return front == rear;
        }

        public int size() {
            return (rear + maxSize - front) % maxSize;
        }

        public void showQueue() {
            if (isEmpty()) {
                System.out.println("队列为空");
                return;
            }
            for (int i = front; i < front + size(); i++) {
                System.out.print(data[i % maxSize] + "\t");
            }
        }

        public int getQueue() {
            if (isEmpty()) {
                throw new RuntimeException("队列为空");
            }
            int value = data[front];
            front = (front + 1) % 10;
            return value;
        }

        public void addQueue(int ele) {
            if (isFull()) {
                System.out.println("队列已经满了");
                return;
            }
            data[rear] = ele;
            rear = (rear + 1) % maxSize;
        }
    }
}
