package com.cloud.designpattern.chainmodel.firstversion;

/**
 * @version v1.0
 * @ClassName OrdinaryPipeline
 * @Author rayss
 * @Datetime 2021/7/20 2:01 下午
 */
public class HaveBreakfastFilter extends AbstractPrepareFilter {

    public HaveBreakfastFilter(AbstractPrepareFilter nextPrepareFilter) {
        super(nextPrepareFilter);
    }

    @Override
    public void prepare(PreparationList preparationList) {
        if (preparationList.isHaveBreakfast()) {
            System.out.println("吃早餐");
        }
    }
}