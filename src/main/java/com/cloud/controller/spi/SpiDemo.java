package com.cloud.controller.spi;

import java.util.ServiceLoader;

/**
 * @version v1.0
 * @ClassName SpiDemo
 * @Author rayss
 * @Datetime 2021/6/23 7:00 下午
 */

public class SpiDemo {

    public static void main(String[] args) {
        ServiceLoader<Datasource> loader = ServiceLoader.load(Datasource.class);
        for (Datasource datasource : loader) {
            System.out.println(datasource.getConnection());
        }
    }
}
