package net.pocrd.demo.mixer;

import net.pocrd.annotation.Description;
import net.pocrd.annotation.DynamicStructure;
import net.pocrd.annotation.HttpDataMixer;
import net.pocrd.responseEntity.DynamicEntity;
import net.pocrd.responseEntity.KeyValueList;

import java.io.Serializable;
import java.util.List;

/**
 * Created by rendong on 2018/5/2.
 */
@HttpDataMixer(name = "mix_A_B_C", desc = "demo for data mixer", owner = "", pagePath = "/index.html")
public class MixDemo {

    @Description("demo entity多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト")
    public static class DemoEntity implements Serializable {
        @Description("id多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト")
        public int    id;
        @Description("name多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト")
        public String name;
    }

    @Description("ComplexTestEntity")
    public static class ComplexTestEntity implements Serializable {
        @Description("strValue")
        public String                 strValue;
        @Description("shortValue")
        public short                  shortValue;
        @Description("byteValue")
        public byte                   byteValue;
        @Description("doubleValue")
        public double                 doubleValue;
        @Description("floatValue")
        public float                  floatValue;
        @Description("boolValue")
        public boolean                boolValue;
        @Description("intValue")
        public int                    intValue;
        @Description("longValue")
        public long                   longValue;
        @Description("charValue")
        public char                   charValue;
        @Description("SimpleTestEntity List")
        public List<SimpleTestEntity> simpleTestEntityList;
        @Description("simpleTestEntity")
        public SimpleTestEntity       simpleTestEntity;
        @Description("dynamic entity")
        @DynamicStructure({ SimpleTestEntity.class, DemoEntity.class, KeyValueList.class })
        public DynamicEntity          dynamicEntity;
        @Description("dynamic entity list")
        @DynamicStructure({ SimpleTestEntity.class, KeyValueList.class })
        public List<DynamicEntity>    dynamicEntityList;
    }

    @Description("simple test entity")
    public static class SimpleTestEntity implements Serializable {
        @Description("string value")
        public String strValue;
        @Description("int array")
        public int[]  intArray;
    }

    @Description("result")
    public static class Result implements Serializable {
        @Description("all in one")
        public String allinone;
    }

    public Result mix(DemoEntity p1, ComplexTestEntity p2, SimpleTestEntity p3) {

        return null;
    }
}
