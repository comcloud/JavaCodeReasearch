package com.cloud.MainTest.jvm;

import com.cloud.MainTest.bean.Person;

/**
 * @version v1.0
 * @ClassName LockEliminationDemo
 * @Author rayss
 * @Datetime 2021/5/7 11:58 上午
 */

public class LockEliminationDemo {
    private void test(){
        Person person = new Person();
        synchronized (person){
            System.out.println(person);
        }
    }
}
