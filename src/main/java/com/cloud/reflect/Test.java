package com.cloud.reflect;

import java.lang.annotation.*;

/**
 * @version v1.0
 * @ClassName Test
 * @Author rayss
 * @Datetime 2021/7/28 3:02 下午
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.CONSTRUCTOR)
public @interface Test {

    String value();
}
