package com.cloud.MainTest.pipline;

import lombok.NonNull;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Function;

/**
 * 使用栈结构的pipeline的builder,在构造之前将声明需要构造的Pipeline依次压入栈中，在最终阶段再依次弹出，
 * 同时最终的一个Pipeline为“EndPipeline”的子类，确保之后不再会有别的Pipeline被调用。
 * @version v1.0
 * @ClassName OrdinaryPipeline
 * @Author rayss
 * @Datetime 2021/7/20 2:01 下午
 */

public final class StackPipelineBuilder<T> {

    private final Deque<Function<Pipeline<? super T>, ? extends Pipeline<T>>> stack =
            new ArrayDeque<>();

    public StackPipelineBuilder<T> add(
            @NonNull Function<Pipeline<? super T>, ? extends ForwardingPipeline<T>> factory) {
        stack.push(factory);
        return this;
    }

    public Pipeline<T> end(@NonNull EndPipeline<T> endPipeline) {
        if (stack.isEmpty()) {
            return endPipeline;
        }
        Pipeline<T> p = endPipeline;
        while (!stack.isEmpty()) {
            p = stack.pop().apply(p);
        }
        return p;
    }
}