package com.cloud.MainTest.meituan;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AscllSort {
    //内部的每个List都是一个单独的省会信息
    //第一个位置就是存储着省份名称
    private static final List<List<String>> province = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //读取文件
        BufferedReader reader = new BufferedReader(new FileReader("/Users/rayss/IdeaProjects/JavaCodeReasearch/src/test/java/com/cloud/MainTest/meituan/yq_out.txt"));
        //写出文件
        BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/rayss/IdeaProjects/JavaCodeReasearch/src/test/java/com/cloud/MainTest/meituan/sort.txt"));

        String line;
        //标记下个位置是不是省会
        //下个是省会的话将会重新创建一个新的集合
        boolean flag = true;
        List<String> element = new ArrayList<>();
        //这个循环就是获取所有信息
        while ((line = reader.readLine()) != null) {
            if (flag) {
                flag = false;
            }
            //这个就是判断是否是省会的逻辑
            if ("".equals(line.trim())) {
                province.add(element);
                element = new ArrayList<>();
                flag = true;
                continue;
            }
            element.add(line);
            element.add("\n");
        }
        //给所有省会开头添加省名
        province.forEach(AscllSort::appendChar);
        //写出文件
        writer.write(province.toString().replace("]", "").replace("[", "").replace(",", ""));
        //将内存中的数据刷新到磁盘
        writer.flush();

    }

    private static void appendChar(List<String> list) {
        //省会名
        String head;
        //从何处开始的变量
        int i;
        if (list.get(0).trim().equals("")) {
            head = list.get(1);
            i = 2;
        } else {
            i = 1;
            head = list.get(0);
        }

        //遍历添加
        for (; i < list.size(); i++) {
            String temp;
            if (!(temp = list.get(i)).trim().equals("")) {
                list.remove(i);
                list.add(i, head + "\t" + temp);
            }
        }
        //移除最开始的省会
        list.remove(0);
    }

}
