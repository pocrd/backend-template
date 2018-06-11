package net.pocrd.demo.entity;

import net.pocrd.define.AbstractReturnCode;

/**
 * Created by guankaiqiang521 on 2014/9/16.
 */
public class DemoReturnCode extends AbstractReturnCode {
    protected DemoReturnCode(String desc, int code) {
        super(desc, code);
    }

    public final static int                _C_DEMO_USER_NOT_FOUND = 1000001;
    public final static AbstractReturnCode DEMO_USER_NOT_FOUND    = new DemoReturnCode(
            "用户找不到. \n en-us:multi-language test \nja-jp:多言語テスト", _C_DEMO_USER_NOT_FOUND);

    public final static int                _C_DEMO_SOMETHING_WRONG = 1000100;
    public final static AbstractReturnCode DEMO_SOMETHING_WRONG    = new DemoReturnCode(
            "有哪里不对. \n en-us:multi-language test \nja-jp:多言語テスト", _C_DEMO_SOMETHING_WRONG);

}
