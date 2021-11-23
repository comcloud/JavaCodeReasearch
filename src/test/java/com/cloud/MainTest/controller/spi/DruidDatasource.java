package com.cloud.MainTest.controller.spi;

/**
 * @version v1.0
 * @ClassName DruidDatasource
 * @Author rayss
 * @Datetime 2021/6/23 7:22 下午
 */

public class DruidDatasource implements Datasource {
    @Override
    public String getConnection() {
        return "druid datasource";
    }
}
