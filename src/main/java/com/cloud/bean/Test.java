/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.bean;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :Test.java v1.0 2021/9/23 2:27 下午 zhangyulei Exp $
 */
public class Test {
    private static Goods[] goodsArray = new Goods[5];

    private static int size = 0;

    private static List<Goods> goodsList = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        Goods goods1 = new Goods();
        Goods goods2 = new Goods();
        //新增物品
        addGoods(goods1);
        addGoods(goods2);
        printArray();
        deleteGoodsByIndex(0);
        printArray();
    }

    public static void addGoods(Goods goods) throws Exception {
        if (size >= goodsArray.length) {
            throw new Exception("数组已满");
        }
//        goodsArray[size++] = goods;
        goodsList.add(goods);
    }

    public static void deleteGoodsByIndex(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("访问索引不合法");
        }
        goodsList.remove(index);
//        goodsArray[index] = null;
        size--;
    }

    public static void printArray(){
//        for (int i = 0; i <= size; i++) {
//            System.out.println(goodsArray[i]);
//        }
        System.out.println(goodsList.toString());
    }
}
