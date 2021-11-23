package com.cloud.MainTest.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 成都犀牛
 * @version 1.0
 * @datetime 2021/2/25 12:39 下午
 */
public class ThreadLocalTest {
    private final List<String> messages = new ArrayList<>();

    public static final ThreadLocal<ThreadLocalTest> holder = ThreadLocal.withInitial(ThreadLocalTest::new);

    private final static ThreadLocal<String> local = new ThreadLocal<>();

    private final static ThreadLocal<Integer> integerLocal = new ThreadLocal<>();

    public static void add(String message) {
        holder.get().messages.add(message);
    }

    private static final int HASH_INCREMENT = 0x61c88647;

    public static List<String> clear() {
        List<String> messages = holder.get().messages;
        holder.remove();

        System.out.println("size: " + holder.get().messages.size());
        return messages;
    }

    public static void main(String[] args) {
        integerLocal.set(27);
        integerLocal.set(28);
        integerLocal.set(29);
        integerLocal.set(30);
        System.out.println(integerLocal.get());
        holder.set(new ThreadLocalTest());

        int hashCode;
        for (int i = 0; i < 16; i++) {
            hashCode = i * HASH_INCREMENT + HASH_INCREMENT;
            int bucket = hashCode & 15;
            System.out.println(bucket);
        }

        local.set("主线程");
        local.set("主线程2");
        local.set("主线程3");

        new Thread(() -> local.set("子线程")).start();
        System.out.println(local.get());

        ThreadLocalTest.add("一枝花算不算浪漫");
        System.out.println(holder.get().messages);
        System.out.println(ThreadLocalTest.clear());
    }
}
