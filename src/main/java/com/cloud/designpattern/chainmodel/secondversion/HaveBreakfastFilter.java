package com.cloud.designpattern.chainmodel.secondversion;

import com.cloud.designpattern.chainmodel.firstversion.PreparationList;

/**
 * @author rayss
 */
public class HaveBreakfastFilter implements StudyPrepareFilter {

    @Override
    public void doFilter(PreparationList preparationList, FilterChain filterChain) {
        if (preparationList.isHaveBreakfast()) {
            System.out.println("吃完早饭");
        }

        filterChain.doFilter(preparationList, filterChain);
    }

}