package com.cloud.designpattern.chainmodel.firstversion;

import lombok.Data;
import lombok.ToString;

/**
 * @version v1.0
 * @ClassName OrdinaryPipeline
 * @Author rayss
 * @Datetime 2021/7/20 2:01 下午
 */
@Data
@ToString
public class PreparationList {

    /**
     * 是否洗脸
     */
    private boolean washFace;

    /**
     * 是否洗头
     */
    private boolean washHair;

    /**
     * 是否吃早餐
     */
    private boolean haveBreakfast;
}