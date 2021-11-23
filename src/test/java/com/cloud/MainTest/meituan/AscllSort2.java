package com.cloud.MainTest.meituan;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AscllSort2 {
    //内部的每个List都是一个单独的省会信息
    //第一个位置就是存储着省份名称
    private static final List<List<String>> province = new ArrayList<>();

    private static String result;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] split = line.split(" ");
        readFileByInFileName(split[0]);
        if (split.length == 3) {
            //指定了要的省份名
            String[] strings = result.split("\n");
            StringBuilder builder = new StringBuilder();
            boolean flag = false;
            for (String string : strings) {
                int indexOf = string.indexOf("\t");
                String substring = indexOf > 0 ? string.substring(0, indexOf) : string;
                substring = substring.replace("\"","").trim();
                if (substring.equals(split[2])) {
                    builder.append(string).append("\n");
                    flag = true;
                } else if (flag) {
                    break;
                }
            }
            writeFileByFileName(split[1], builder.toString());
        } else {
            writeFileByFileName(split[1]);
        }

    }

    private static void readFileByInFileName(String fileName) {
        //读取文件
        try (BufferedReader reader = new BufferedReader(
                new FileReader("/Users/rayss/IdeaProjects/JavaCodeReasearch/src/test/java/com/cloud/MainTest/meituan/" + fileName))) {
            //写出文件
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
        } catch (IOException ignored) {
        }
        //给所有省会开头添加省名
        province.forEach(AscllSort2::appendChar);
        result = province.toString().replace("]", "").replace("[", "").replace(",", "");
    }

    private static void writeFileByFileName(String outFileName) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/rayss/IdeaProjects/JavaCodeReasearch/src/test/java/com/cloud/MainTest/meituan/" + outFileName))) {
            //写出文件
            writer.write(result);
            //将内存中的数据刷新到磁盘
            writer.flush();
        } catch (IOException ignored) {
        }
    }

    private static void writeFileByFileName(String outFileName, String content) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/rayss/IdeaProjects/JavaCodeReasearch/src/test/java/com/cloud/MainTest/meituan/" + outFileName))) {
            //写出文件
            writer.write(content);
            //将内存中的数据刷新到磁盘
            writer.flush();
        } catch (IOException ignored) {
        }
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
