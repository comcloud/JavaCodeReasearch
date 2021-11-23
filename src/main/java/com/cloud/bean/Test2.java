/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.bean;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :Test2.java v1.0 2021/9/23 3:09 下午 zhangyulei Exp $
 */
public class Test2 {

    private static List<Goods> goodsList = new ArrayList<>(10);

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("1", "2");
        Set<Map.Entry<String, String>> entries = map.entrySet();

        Goods goods1 = new Goods();
        goods1.setCode("1");
        Goods goods2 = new Goods();
        goods2.setCode("2");
        //新增物品
        add(goods1);
        add(goods2);
        System.out.println(queryGoodsByCode("2"));
    }

    public static void add(Goods goods) {
        List<Goods> temp = new ArrayList<>(goodsList);
        temp.add(goods);
        Set<Goods> goodsSet = new HashSet<>(temp);
        if (goodsSet.size() != temp.size()) {
            return;
        }
        goodsList.add(goods);
    }

    public static void delete() {
        Iterator<Goods> iterator = goodsList.iterator();
        while (iterator.hasNext()) {
            iterator.remove();
        }
    }

    public static Goods queryGoodsByCode(String code){
        AtomicReference<Goods> goods = new AtomicReference<>();
        goodsList.forEach(g -> {
            if(g.getCode().equals(code)){
                goods.set(g);
            }
        });
        return goods.get();
    }
}
