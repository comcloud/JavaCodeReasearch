package com.cloud.MainTest.pipline;


/**
 * 表示无下游的pipeline
 * @version v1.0
 * @ClassName OrdinaryPipeline
 * @Author rayss
 * @Datetime 2021/7/20 2:01 下午
 */
public abstract class EndPipeline<T> implements Pipeline<T> {

  private final String name;

  public EndPipeline(String name) {
    this.name = name;
  }

  @Override
  public abstract void process(PipelineContext ctx, T t);

  @Override
  public final void forward(PipelineContext ctx, T t) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String toString() {
    return name;
  }
}