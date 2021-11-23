/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.MainTest.util;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :LruCacheTest.java v1.0 2021/10/8 8:00 下午 zhangyulei Exp $
 */
public class LruCacheTest {
    public static void main(String[] args) {
        LruCache cache = new LruCache(2);
        cache.put(2, 1);
        cache.put(1, 1);
        cache.put(2, 3);
        cache.put(4, 1);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }
}
