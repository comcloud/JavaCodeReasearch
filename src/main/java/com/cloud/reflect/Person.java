package com.cloud.reflect;

import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Repeatable;

/**
 * @version v1.0
 * @ClassName Person
 * @Author rayss
 * @Datetime 2021/7/28 2:35 下午
 */

@Slf4j
public class Person extends BasePerson{
    private String name;
    public int age;

    static {
        System.out.println("静态代码块加载");
    }

    {
        System.out.println("普通代码块加载");
    }

    @Test("我是自定义注解")
    public Person(){
        System.out.println("构造方法加载");
    }

    public void print(){
        System.out.println(this.name + " : " + this.age);
    }

}
