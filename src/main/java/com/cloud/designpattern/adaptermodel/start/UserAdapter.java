package com.cloud.designpattern.adaptermodel.start;

/**
 * @version v1.0
 * @ClassName UserAdapter
 * @Author rayss
 * @Datetime 2021/8/8 4:27 下午
 */

public class UserAdapter implements UserInterface{

    private final UserInfo userInfo;

    public UserAdapter(UserInfo userInfo){
        this.userInfo = userInfo;
    }

    @Override
    public String getName() {
        return userInfo.getUserBaseInfo().get("name");
    }

    @Override
    public String getTelNumber() {
        return userInfo.getUserBaseInfo().get("telNumber");
    }
}
