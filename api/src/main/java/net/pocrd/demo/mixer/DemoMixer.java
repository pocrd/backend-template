package net.pocrd.demo.mixer;

import net.pocrd.annotation.HttpDataMixer;

/**
 * Created by rendong on 2018/4/20.
 */
@HttpDataMixer(name = "demo.demoMix", desc = "测试mixer", owner = "demo", pagePath = "/demo.html")
public class DemoMixer {
    public static MixEntity.Result mix(MixEntity.A a, MixEntity.B b) {
        MixEntity.Result r = new MixEntity.Result();
        r.a = a;
        r.b = b;
        return r;
    }
}
