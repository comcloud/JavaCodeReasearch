package com.cloud.MainTest.controller.spi;

/**
 * @version v1.0
 * @ClassName OrcaleDatasource
 * @Author rayss
 * @Datetime 2021/6/23 7:21 下午
 */

public class OracleDatasource implements Datasource{

    @Override
    public String getConnection() {
        return "oracle datasource";
    }
}
