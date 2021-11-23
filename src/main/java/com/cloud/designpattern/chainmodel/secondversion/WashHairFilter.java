package com.cloud.designpattern.chainmodel.secondversion;

import com.cloud.designpattern.chainmodel.firstversion.PreparationList;

/**
 * @version v1.0
 * @ClassName StudyPrepareFilter
 * @Author rayss
 * @Datetime 2021/7/21 9:24 上午
 */
public class WashHairFilter implements StudyPrepareFilter {

    @Override
    public void doFilter(PreparationList preparationList, FilterChain filterChain) {
        if (preparationList.isWashHair()) {
            System.out.println("洗完头发");
        }

        filterChain.doFilter(preparationList, filterChain);
    }

}