package com.cloud.leetcode;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @version v1.0
 * @ClassName LRUCache
 * @Author rayss
 * @Datetime 2021/8/17 11:22 上午
 */

public class LruCache {
    public static void main(String[] args) {
        LruCache cache = new LruCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println("cache.get(1) = " + cache.get(1));
        cache.put(3, 3);
        System.out.println("cache.get(2) = " + cache.get(2));
        cache.put(4, 4);
        System.out.println("cache.get(1) = " + cache.get(1));
        System.out.println("cache.get(3) = " + cache.get(3));
        System.out.println("cache.get(4) = " + cache.get(4));
    }

    private Node head;
    private final int capacity;
    private int size;


    public LruCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        //遍历链表
        Node temp = head;
        while (temp != null) {
            if (temp.key == key) {
                int result = temp.value;
                swap(key, temp);
                return result;
            }
            temp = temp.next;
        }
        return -1;
    }

    /**
     * 将最近访问的存放到头部
     */
    private void swap(int key, Node temp) {
        int tempKey = head.key;
        head.key = key;
        int tempValue = head.value;
        head.value = temp.value;
        temp.key = tempKey;
        temp.value = tempValue;
    }

    public void put(int key, int value) {
        Node node = new Node(key, value);
        if (capacity == 0) {
            return;
        }
        if (head == null) {
            head = node;
        } else {
            Node temp = head;
            if (capacity == size) {
                //此时说明缓存已经满，就需要遍历节点找到时间戳最小的然后进行覆盖
                Node maxTimestampNode = temp;
                while (temp != null){
                    if(maxTimestampNode.timestamp < temp.timestamp){
                        maxTimestampNode = temp;
                    }
                    temp = temp.next;
                }
                maxTimestampNode.timestamp = System.nanoTime();
                maxTimestampNode.key = key;
                maxTimestampNode.value = value;
            } else {
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = node;
            }
        }
        size += size == capacity ? 0 : 1;
    }


    private static class Node {
        private int key;
        private int value;
        private long timestamp = System.nanoTime();
        private Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

}
