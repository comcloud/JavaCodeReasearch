package com.cloud.designpattern.builder;

import java.io.*;
import java.util.concurrent.*;

/**
 * @version v1.0
 * @ClassName BuilderModel
 * @Author rayss
 * @Datetime 2021/7/27 4:38 下午
 */

public class BuilderModel {

    public static void main(String[] args) throws FileNotFoundException {
        Person person = new Person.PersonBuilder().setAge(10).setName("123").build();
        System.out.println(person);

        BufferedReader reader = new BufferedReader(new FileReader(""));

        ThreadPoolExecutor executor = new ThreadPoolExecutor(4
                , 10
                , 0L
                , TimeUnit.SECONDS
                , new LinkedBlockingQueue(2000)
                , r -> new Thread("echo" + r), new ThreadPoolExecutor.CallerRunsPolicy());

        executor.shutdownNow();
    }
}
