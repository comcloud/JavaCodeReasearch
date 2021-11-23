package com.cloud.designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @version v1.0
 * @ClassName CustomInvocationHandler
 * @Author rayss
 * @Datetime 2021/6/20 5:00 下午
 */

public class CustomInvocationHandler implements InvocationHandler {
    private final Object targetObject;

    private BeforeProcessor beforeProcessor;
    private AfterProcessor afterProcessor;

    public CustomInvocationHandler(Object targetObject) {
        this.targetObject = targetObject;
    }

    /**
     * @param beforeProcessor 前置增强对象
     * @param afterProcessor 后置增强对象
     * @return 代理之后的对象
     */
    public Object getProxyTarget(BeforeProcessor beforeProcessor, AfterProcessor afterProcessor) {
        this.beforeProcessor = beforeProcessor;
        this.afterProcessor = afterProcessor;
        return Proxy.newProxyInstance(CustomInvocationHandler.class.getClassLoader(), SchoolService.class.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        beforeProcessor.beforeProcess();
        Object methodResult = method.invoke(targetObject, args);
        afterProcessor.afterProcess();
        return methodResult;
    }
}
