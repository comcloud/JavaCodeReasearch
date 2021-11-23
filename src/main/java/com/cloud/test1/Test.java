/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.test1;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :Test.java v1.0 2021/11/11 4:05 下午 zhangyulei Exp $
 */
public class Test {
    public static void main(String[] args) {
        Student student = new Student("玛咖", "女", "清华");
        CollegeStudent collegeStudent = new CollegeStudent("玛咖", "女", "清华", "建模");
        student.study();
        collegeStudent.study();

    }
}
