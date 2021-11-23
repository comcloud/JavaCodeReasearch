package com.cloud.MainTest.pipline;

import java.util.List;

/**
 * @version v1.0
 * @ClassName OrdinaryPipeline
 * @Author rayss
 * @Datetime 2021/7/20 2:01 下午
 */

public abstract class OrdinaryPipeline<T> implements Pipeline<T> {

    private final String name;
    /**
     * 下游
     */
    private Pipeline<? super T> next;

    public OrdinaryPipeline(String name) {
        this.name = name;
    }

    public void setNext(Pipeline<? super T> next) {
        this.next = next;
    }

    @Override
    public abstract void process(PipelineContext ctx, T t);

    @Override
    public final void forward(PipelineContext ctx, T t) {
        if (next != null) {
            next.process(ctx, t);
        }
    }

    @Override
    public String toString() {
        if (next != null) {
            return name + "->" + next.toString();
        }
        return name;
    }

    public static <T> Pipeline<T> getInstance(
            List<? extends OrdinaryPipeline<? extends T>> pipelines) {
        if (pipelines == null || pipelines.isEmpty()) {
            throw new IllegalArgumentException("empty pipelines!");
        }
        @SuppressWarnings("unchecked")
        OrdinaryPipeline<T>[] a = (OrdinaryPipeline<T>[]) pipelines.toArray(new OrdinaryPipeline[0]);
        OrdinaryPipeline<T> p = a[0];
        //构建上下游关系，上每个next指向下一个
        for (int i = 1; i < a.length; ++i) {
            p.setNext(a[i]);
            p = a[i];
        }
        p.setNext(null);
        return a[0];
    }
}
