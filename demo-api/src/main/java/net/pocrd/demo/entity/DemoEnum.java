package net.pocrd.demo.entity;

import net.pocrd.annotation.Description;

/**
 * Created by rendong on 16/3/16.
 */
@Description("字母枚举多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト")
public enum DemoEnum {
    @Description("this is A多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト")
    A,
    @Description("this is B多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト")
    B,
    @Description("this is C多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト")
    C,
    @Description("this is D多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト")
    D
}
