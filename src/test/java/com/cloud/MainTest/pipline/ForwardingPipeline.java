package com.cloud.MainTest.pipline;

import lombok.NonNull;


/**
 * 表示有下游的pipeline
 * @version v1.0
 * @ClassName OrdinaryPipeline
 * @Author rayss
 * @Datetime 2021/7/20 2:01 下午
 */
public abstract class ForwardingPipeline<T> implements Pipeline<T> {

  private final String name;

  private final Pipeline<? super T> next;

  public ForwardingPipeline(@NonNull String name, @NonNull Pipeline<? super T> next) {
    this.name = name;
    this.next = next;
  }

  @Override
  public abstract void process(PipelineContext ctx, T t);

  @Override
  public void forward(PipelineContext ctx, T t) {
    next.process(ctx, t);
  }

  @Override
  public String toString() {
    return name + "->" + next.toString();
  }
}