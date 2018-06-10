package net.pocrd.demo.mixer;

import net.pocrd.annotation.HttpDataMixer;
import net.pocrd.define.ApiMixer;
import net.pocrd.define.Evaluator;
import net.pocrd.util.EvaluatorProvider;

import java.util.ArrayList;

/**
 * Created by rendong on 2018/4/20.
 */
@HttpDataMixer(name = "demo.demoMix", desc = "测试mixer", owner = "demo", pagePath = "/demo.html")
public class DemoMixer {

    public static MixEntity.Result mix(MixEntity.P1 p1, MixEntity.P2 p2) {

        return null;
    }

    public static class DemoMixer1 implements ApiMixer {

        public Object execute(Object[] args) {
            MixEntity.P1 o1;
            MixEntity.P2 o2;

            if (args[0] == null) {
                o1 = null;
            } else {
                o1 = new MixEntity.P1();
                EvaluatorProvider.getEvaluator(MixEntity.P1.class, args[0].getClass()).evaluate(o1, null);
            }

            if (args[1] == null) {
                o2 = null;
            } else {
                o2 = new MixEntity.P2();
                EvaluatorProvider.getEvaluator(MixEntity.P2.class, args[0].getClass()).evaluate(o2, null);
            }

            return net.pocrd.demo.mixer.DemoMixer.mix(o1, o2);
        }
    }

    /**
     * sample code for evaluator code generation.
     */
    public void evaluate(MixEntity.P1 p1, MixEntity.P2 p2) {

        if (p1 != null && p2 != null) {
            if (p2.ps != null) {
                if (p1.ps == null) {
                    p1.ps = new ArrayList<MixEntity.P1>(p2.ps.size());
                }
                int i = 0;
                for (MixEntity.P2 pp2 : p2.ps) {
                    MixEntity.P1 pp1;
                    if (p1.ps.size() > i) {
                        pp1 = p1.ps.get(i);
                    } else {
                        pp1 = new MixEntity.P1();
                        p1.ps.add(pp1);
                    }
                    getP1P2().evaluate(pp1, pp2);
                    i++;
                }
            }

            if (p2.p != null) {
                if (p1.p == null) {
                    p1.p = new MixEntity.P1();
                }
                getP1P2().evaluate(p1.p, p2.p);
            }
        }

        return;
    }

    private Evaluator p1p2;

    public Evaluator getP1P2() {
        if (p1p2 == null) {
            p1p2 = EvaluatorProvider.getEvaluator(MixEntity.P1.class, MixEntity.P2.class);
        }
        return p1p2;
    }

}
