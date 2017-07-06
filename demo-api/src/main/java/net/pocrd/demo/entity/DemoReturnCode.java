package net.pocrd.demo.entity;

import net.pocrd.entity.AbstractReturnCode;

/**
 * Created by guankaiqiang521 on 2014/9/16.
 */
public class DemoReturnCode extends AbstractReturnCode {
    protected DemoReturnCode(String desc, int code) {
        super(desc, code);
    }

    public final static int                _C_DEMO_UNKNOW_ERROR = 1;
    public final static AbstractReturnCode DEMO_UNKNOW_ERROR    = new DemoReturnCode(
            "demo unkown error多语言测试. \n en-us:multi-language test \nja-jp:多言語テスト", _C_DEMO_UNKNOW_ERROR);

    public final static int                _C_DEMO_DEVICE_DENIED = 100;
    public final static AbstractReturnCode DEMO_DEVICE_DENIED    = new DemoReturnCode(
            "device denied多语言测试. \n en-us:multi-language test \nja-jp:多言語テスト", _C_DEMO_DEVICE_DENIED);

}
