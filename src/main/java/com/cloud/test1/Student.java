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
 * @version :Student.java v1.0 2021/11/11 4:00 下午 zhangyulei Exp $
 */
public class Student {

    private String xm;

    private String xh;

    private String xx;

    public Student(String xm) {
        this.xm = xm;
    }

    public Student(String xm, String xh, String xx) {
        this.xm = xm;
        this.xh = xh;
        this.xx = xx;
    }

    public String study(){
        String res = this.xm + "好好学习天天向上";
        System.out.println(res);
        return res;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getXx() {
        return xx;
    }

    public void setXx(String xx) {
        this.xx = xx;
    }
}
