package com.cloud.MainTest.controller.spi;

import com.cloud.MainTest.bean.Singleton;

import java.util.ServiceLoader;

/**
 * @version v1.0
 * @ClassName SpiDemo
 * @Author rayss
 * @Datetime 2021/6/23 7:00 下午
 */

public class SpiDemo {
    private static ServiceLoader<Datasource> loader = ServiceLoader.load(Datasource.class);

    public static void main(String[] args) {
        loader.reload();
        for (Datasource datasource : loader) {
            System.out.println(datasource.getConnection());
        }
    }
}
