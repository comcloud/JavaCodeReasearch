package com.cloud.designpattern.chainmodel.secondversion;

import com.cloud.designpattern.chainmodel.firstversion.PreparationList;

/**
 * @author rayss
 */
public class WashFaceFilter implements StudyPrepareFilter {

    @Override
    public void doFilter(PreparationList preparationList, FilterChain filterChain) {
        if (preparationList.isWashFace()) {
            System.out.println("洗完脸");
        }

        filterChain.doFilter(preparationList, filterChain);
    }

}