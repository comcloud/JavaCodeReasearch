package com.cloud.reflect;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Arrays;

/**
 * @version v1.0
 * @ClassName Client
 * @Author rayss
 * @Datetime 2021/7/28 2:32 下午
 */
@Slf4j
public class Client {

    /**
     * 检测一个类通过不同的加载方式查看一个类加载状态
     */
    @Test
    public void classOrderTest() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //通过参数设置是否会初始化这个类，初始化的作用就是是否会执行静态代码块
        Class<?> forNameClass = Class.forName("com.cloud.reflect.Person", false, this.getClass().getClassLoader());
        System.out.println("-------------------");
        forNameClass.newInstance();

        //直接获取对应的Class对象，但是不会对Person对象有任何初始化的操作
        Class<Person> aClass = Person.class;
        System.out.println("-------------------");
        aClass.newInstance();

        //通过对象的方式调用对应的getClass方法不会有对对象的初始化操作等
        Person person = new Person();
        Class<? extends Person> getClass = person.getClass();
        System.out.println("-------------------");
        getClass.newInstance();
    }

    @Test
    public void commonMethodTest() throws NoSuchFieldException {
        Class<Person> personClass = Person.class;
        //凡是：getDeclaredXXX方式都是获取自己类中所有定义的内容，无论他的访问权限如何
        Field[] declaredFields = personClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }
        System.out.println("----------------------");
        //获取对自己类可见的属性，包括protected，但是对于private访问权限的属性即使是自己定义的也依旧无法访问到
        Field[] fields = personClass.getFields();
        System.out.println(Arrays.toString(fields));

        System.out.println("----------------------");
        Annotation[] annotations = personClass.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }

    }
}
