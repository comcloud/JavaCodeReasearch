package com.cloud.designpattern.builder;

import lombok.ToString;

/**
 * @version v1.0
 * @ClassName Person
 * @Author rayss
 * @Datetime 2021/7/27 4:38 下午
 */

@ToString
public class Person {

    private String name;
    private int age;

    public Person(PersonBuilder builder){
        this.name = builder.name;
        this.age = builder.age;
    }

    public static class PersonBuilder{
        private String name;
        private int age;

        public Person build(){
            return new Person(this);
        }

        public PersonBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public PersonBuilder setAge(int age) {
            this.age = age;
            return this;
        }
    }
}
