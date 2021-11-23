package com.cloud.designpattern.chainmodel.secondversion;

import com.cloud.designpattern.chainmodel.firstversion.PreparationList;

/**
 * @version v1.0
 * @ClassName StudyPrepareFilter
 * @Author rayss
 * @Datetime 2021/7/21 9:24 上午
 */

public interface StudyPrepareFilter {

    void doFilter(PreparationList preparationList, FilterChain filterChain);
}
