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
 * @version :CollegeStudent.java v1.0 2021/11/11 4:02 下午 zhangyulei Exp $
 */
public class CollegeStudent extends Student{

    private String ah;

    public static String clazz;

    public CollegeStudent(String xm, String ah) {
        super(xm);
        this.ah = ah;
    }

    public CollegeStudent(String xm, String xh, String xx, String ah) {
        super(xm, xh, xx);
        this.ah = ah;
    }

    @Override
    public String study() {
        String res = "爱好：" + ah + "\t" + super.study();
        System.out.println(res);
        return res;
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public static String getClazz() {
        return clazz;
    }

    public static void setClazz(String clazz) {
        CollegeStudent.clazz = clazz;
    }
}
