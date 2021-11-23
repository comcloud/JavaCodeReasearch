package com.cloud.MainTest.util;

import java.io.*;
import java.util.Scanner;

/**
 * @version v1.0
 * @ClassName ReadTextToDatabase
 * @Author rayss
 * @Datetime 2021/6/30 9:16 上午
 */

public class ReadTextToDatabase {

    public static void main(String[] args) {
    }

    public static void readTextToDatabase(String filename) {
        readTextToDatabase(new File(filename));
    }

    public static void readTextToDatabase(File file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gbk"))) {
            String line = "";
            System.out.println("书名：" + file.getName().substring(0, file.getName().lastIndexOf(".")));
            while ((line = reader.readLine()) != null) {
                if (line.contains("作者")) {
                    System.out.println("本书的作者是：" + line.trim());
                }

            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

}
