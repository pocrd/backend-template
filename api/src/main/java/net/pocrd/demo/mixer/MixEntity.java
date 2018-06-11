package net.pocrd.demo.mixer;

import java.util.List;

/**
 * Created by rendong on 2018/6/5.
 */
public class MixEntity {
    public static class Result {
        public A a;
        public B b;
    }

    public static class A {
        public List<A> ps;
        public A       p;

    }

    public static class B {
        public List<B> ps;
        public B       p;
    }
}
