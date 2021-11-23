package com.cloud.designpattern.adaptermodel.start;

import java.util.Map;

/**
 * @version v1.0
 * @ClassName UserInfo
 * @Author rayss
 * @Datetime 2021/8/8 4:20 下午
 */

public class UserInfo {
    private Map<String,String> userBaseInfo;

    public Map<String,String> getUserBaseInfo(){
        return userBaseInfo;
    }

    public void setUserBaseInfo(Map<String, String> userBaseInfo) {
        this.userBaseInfo = userBaseInfo;
    }
}
