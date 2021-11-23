package com.cloud.frequentlyused;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 贪心算法示例，现在问题解决一个广播问题，尽可能覆盖全部地区然后以最少电台
 *
 * @version v1.0
 * @ClassName Broadcast
 * @Author rayss
 * @Datetime 2021/6/7 12:05 上午
 */

public class Broadcast {

    public static void main(String[] args) {
        Map<String, Set<String>> map = new HashMap<>(10);
        map.put("k1", createSet("北京", "上海", "天津"));
        map.put("k2", createSet("广州", "北京", "深圳"));
        map.put("k3", createSet("成都", "上海", "杭州"));
        map.put("k4", createSet("上海", "天津"));
        map.put("k5", createSet("杭州", "大连"));

        Set<String> allAreas = new HashSet<>();
        map.values().forEach(allAreas::addAll);

        //存放选择的电台集合
        List<String> selectAll = new ArrayList<>();
        //定义一个临时集合，存放每次判断当前广播覆盖地区与所有地区的交集
        Set<String> tempSet = new HashSet<>();

        //只要所有地区还有内容说明还有地区没有覆盖到，那么就继续循环
        while (allAreas.size() != 0) {
            AtomicReference<String> maxKey = new AtomicReference<>("");
            map.forEach((k, v) -> {
                tempSet.clear();
                tempSet.addAll(v);
                //此时tempSet与allAreas的交集就会出现在tempSet中，即起到此时广播覆盖的内容区域
                tempSet.retainAll(allAreas);
                //体现贪心算法的位置，每次都是获取到最优解
                if (tempSet.size() > 0
                        && ("".equals(maxKey.get()) || map.get(maxKey.get()).size() < tempSet.size())) {
                    maxKey.set(k);
                }
            });
            if (!"".equals(maxKey.get())) {
                //此时说明maxKey已经进行了变换
                selectAll.add(maxKey.get());
                //移除all area中已经覆盖的内容
                allAreas.removeAll(map.get(maxKey.get()));
            }
        }
        System.out.println(selectAll);
    }

    private static Set<String> createSet(String... set) {
        return new HashSet<>(Arrays.asList(set));
    }

}
