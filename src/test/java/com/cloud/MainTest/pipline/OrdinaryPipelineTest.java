package com.cloud.MainTest.pipline;

import lombok.NonNull;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;

/**
 * @version v1.0
 * @ClassName OrdinaryPipelineTest
 * @Author rayss
 * @Datetime 2021/7/20 2:05 下午
 */

public class OrdinaryPipelineTest {

    @Test
    public void test() {
        Pipeline<?> pipeline =
                OrdinaryPipeline.getInstance(
                        Arrays.asList(new DemoPipeline("1"), new DemoPipeline("2"), new DemoPipeline("3")));
        System.out.println(pipeline);
    }

    private static final class DemoPipeline extends OrdinaryPipeline<String> {

        public DemoPipeline(String name) {
            super(name);
        }

        @Override
        public void process(PipelineContext ctx, String s) {
            // TODO
        }
    }


    //----------------------- 改进后------------------------
    private static final class F extends ForwardingPipeline<String> {

        public F(@NonNull String name, @NonNull Pipeline<? super String> next) {
            super(name, next);
        }

        @Override
        public void process(PipelineContext ctx, String s) {
            // TODO
        }
    }

    private static final class E extends EndPipeline<String> {

        public E(String name) {
            super(name);
        }

        @Override
        public void process(PipelineContext ctx, String s) {
            // TODO
        }
    }

    @Test
    public void testStack() {
        Pipeline<String> pipeline =
                new StackPipelineBuilder<String>()
                        .add(next -> new F("1", next))
                        .add(next -> new F("2", next))
                        .end(new E("3"));
        System.out.println(pipeline);
    }

    @Test
    public void testFunctional() {
        Map<String,String> map  = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("我是list的数据");
        String[] strings = {"1","2","3","4","5"};
        System.out.println(Arrays.toString(list.toArray(strings)));

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            iterator.next();
            iterator.remove();
        }
        System.out.println(list);

        Pipeline<String> p1 = new FunctionalPipelineBuilder<String, Pipeline<String>>().end(new E("1"));

        Pipeline<String> p2 =
                new FunctionalPipelineBuilder<String, Pipeline<String>>()
                        .add(next -> new F("1", next))
                        .add(next -> new F("2", next))
                        .end(new E("3"));

        System.out.println(p1);
        System.out.println(p2);
    }
}
