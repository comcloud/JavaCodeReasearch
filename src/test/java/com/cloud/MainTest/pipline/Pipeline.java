package com.cloud.MainTest.pipline;

/**
 * @version v1.0
 * @ClassName Pipline
 * @Author rayss
 * @Datetime 2021/7/20 1:59 下午
 */

public interface Pipeline<T> {
    /**
     * 处理当前的逻辑
     * @param ctx 上下文
     * @param t 处理数据
     */
    void process(PipelineContext ctx, T t);

    /**
     * 将处理消息转发给下游的流程：上游可以控制消息是否转发给下游
     * @param ctx 上下文
     * @param t 处理数据
     * */
    void forward(PipelineContext ctx, T t);
}
