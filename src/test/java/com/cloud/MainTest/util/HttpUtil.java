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
 * @version :HttpUtil.java v1.0 2021/11/2 9:17 下午 zhangyulei Exp $
 */
public class HttpUtil {

    public static String get(String url) {
        String method = "get";
        url = url + "1";
        return get(url, method);
    }

    public static String get(String url, String method) {
        String param = "1";
        return get(url, method, param);
    }

    public static String get(String url, String method, String param) {

        ///....
        return "";
    }
}
