package com.cloud.designpattern.chainmodel.firstversion;


/**
 * @version v1.0
 * @ClassName OrdinaryPipeline
 * @Author rayss
 * @Datetime 2021/7/20 2:01 下午
 */
public abstract class AbstractPrepareFilter {

    private final AbstractPrepareFilter nextPrepareFilter;

    public AbstractPrepareFilter(AbstractPrepareFilter nextPrepareFilter) {
        this.nextPrepareFilter = nextPrepareFilter;
    }

    public void doFilter(PreparationList preparationList, Study study) {
        prepare(preparationList);

        if (nextPrepareFilter == null) {
            study.study();
        } else {
            nextPrepareFilter.doFilter(preparationList, study);
        }
    }

    public abstract void prepare(PreparationList preparationList);

}