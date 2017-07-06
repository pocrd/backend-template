package net.pocrd.demo.entity;

import net.pocrd.annotation.Description;

import java.io.Serializable;

/**
 * Created by guankaiqiang521 on 2014/9/29.
 */
@Description("demo entity多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト")
public class DemoEntity implements Serializable {
    @Description("id多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト")
    public int    id;
    @Description("name多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト")
    public String name;
}
