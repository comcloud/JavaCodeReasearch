package com.cloud.designpattern.chainmodel.secondversion;

import com.cloud.designpattern.chainmodel.firstversion.PreparationList;
import com.cloud.designpattern.chainmodel.firstversion.Study;

import java.util.ArrayList;
import java.util.List;

/**
 * @version v1.0
 * @ClassName StudyPrepareFilter
 * @Author rayss
 * @Datetime 2021/7/21 9:24 上午
 */
public class FilterChain implements StudyPrepareFilter {

    private int pos = 0;

    private Study study;

    private List<StudyPrepareFilter> studyPrepareFilterList;

    public FilterChain(Study study) {
        this.study = study;
    }

    public void addFilter(StudyPrepareFilter studyPrepareFilter) {
        if (studyPrepareFilterList == null) {
            studyPrepareFilterList = new ArrayList<>();
        }

        studyPrepareFilterList.add(studyPrepareFilter);
    }

    @Override
    public void doFilter(PreparationList thingList, FilterChain filterChain) {
        // 所有过滤器执行完毕
        if (pos == studyPrepareFilterList.size()) {
            study.study();
        }

        studyPrepareFilterList.get(pos++).doFilter(thingList, filterChain);
    }

}