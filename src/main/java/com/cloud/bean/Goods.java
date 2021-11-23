/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :Goods.java v1.0 2021/9/23 2:26 下午 zhangyulei Exp $
 */
@Data
public class Goods {
    /**
     * 编号，全局唯一
     */
    private Long id;

    /**
     * 物品名称，可重复
     */
    private String name;

    /**
     * 物品编码，全局唯一
     */
    private String code;

    /**
     * 启用或者停用
     */
    private boolean enable;

    /**
     * 生产日期
     */
    private Date productionDate;

    /**
     * 保质期天数
     */
    private Integer shelfLifeDay;

    /**
     * 物品分类，如水果、肉类
     */
    private GoodsCategoryEnum category;

    /**
     * 物品每默认单位的价格
     */
    private BigDecimal price;

    /**
     * 物品重量
     */
    private double weight;
}
