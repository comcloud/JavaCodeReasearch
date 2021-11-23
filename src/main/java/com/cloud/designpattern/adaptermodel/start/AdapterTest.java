package com.cloud.designpattern.adaptermodel.start;

import java.util.HashMap;
import java.util.Map;

/**
 * @version v1.0
 * @ClassName AdapterTest
 * @Author rayss
 * @Datetime 2021/8/8 4:22 下午
 */

public class AdapterTest {
    public static void main(String[] args) {
        UserInfo info = new UserInfo();
        Map<String,String> userInfo = new HashMap<String, String>(2) {
            {
                put("name","corn");
                put("telNumber","1123423423");
            }
        };
        info.setUserBaseInfo(userInfo);



        //最开始的方式，直接获取info中的map
        Map<String, String> userBaseInfo = info.getUserBaseInfo();
        System.out.println(userBaseInfo.get("name"));
        System.out.println(userBaseInfo.get("telNumber"));


        //后面的方式
        /*
        * 定义一种中间类，用来将原有不合适的类转换为想要的结果
        * 这里的例子最开始只是从userinfo对象中获取内容，但是因为需求改变，现在需要
        * 定义两个方法getName以及getTelNumber方法，因为不对原先的代码进行改动，所以
        * 定义一个新的适配器类UserAdapter以及一个新的接口类UserInterface
        * 适配器实现接口类，这里使用组合的方法而不是继承，所以我们每次都是创建一个适配器类
        * 然后去调用适配器类的方法获取对应的结果
        * */
        UserAdapter adapter = new UserAdapter(info);
        System.out.println(adapter.getName());
        System.out.println(adapter.getTelNumber());

    }
}
