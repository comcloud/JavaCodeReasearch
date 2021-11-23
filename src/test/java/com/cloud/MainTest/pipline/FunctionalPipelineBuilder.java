package com.cloud.MainTest.pipline;

import java.util.function.Function;

/**
 * @version v1.0
 * @ClassName OrdinaryPipeline
 * @Author rayss
 * @Datetime 2021/7/20 2:01 下午
 */
public final class FunctionalPipelineBuilder<T, P extends Pipeline<T>> {

    private final Function<Pipeline<? super T>, P> factory;

    @SuppressWarnings("unchecked")
    public FunctionalPipelineBuilder() {
        this((Pipeline<? super T> next) -> (P) next);
    }

    private FunctionalPipelineBuilder(Function<Pipeline<? super T>, P> nextFactory) {
        this.factory = nextFactory;
    }

    public FunctionalPipelineBuilder<T, P> add(
            Function<Pipeline<? super T>, ? extends ForwardingPipeline<T>> nextFactory) {
        return new FunctionalPipelineBuilder<>(factory.compose(nextFactory));
    }

    public P end(EndPipeline<T> endPipeline) {
        return factory.apply(endPipeline);
    }
}