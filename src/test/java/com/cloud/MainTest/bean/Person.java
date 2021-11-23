package com.cloud.MainTest.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author 成都犀牛
 * @version 1.0
 * @datetime 2021/2/22 12:00 下午
 */
@Data
@NoArgsConstructor
@Builder
public class Person implements Serializable {
//    private static final long serialVersionUID = 1L;

    private int age;
    public volatile String name;
    private boolean sex;
    private List<Person> child;

    public Person(int age, String name, boolean sex, List<Person> child) {
        this.age = age;
        this.name = name;
        this.sex = sex;
        this.child = child;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && sex == person.sex && Objects.equals(name, person.name) && Objects.equals(child, person.child);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name, sex, child);
    }
}
