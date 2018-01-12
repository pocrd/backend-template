// Auto Generated.  DO NOT EDIT!

package net.pocrd.m.app.client.api.request;

import com.google.gson.*;

import net.pocrd.m.app.client.LocalException;
import net.pocrd.m.app.client.BaseRequest;
import net.pocrd.m.app.client.SecurityType;
import net.pocrd.m.app.client.api.resp.*;

/**
 * demo test多语言测试. 
en-us:multi-language test 
ja-jp:多言語テスト
 * 
 * @author demo
 *
 */
public class Demo_SayHello extends BaseRequest<Api_DEMO_DemoEntity> {
    
    /**
     * 当前请求的构造函数，以下参数为该请求的必填参数
     * @param name say hello多语言测试. 
en-us:multi-language test 
ja-jp:多言語テスト
     */
    public Demo_SayHello(String name) {
        super("demo.sayHello", SecurityType.None);

        try {
            params.put("name", name);
        } catch(Exception e) {
            throw new LocalException("SERIALIZE_ERROR", LocalException.SERIALIZE_ERROR, e);
        }
    }
    
    /**
     * 私有的默认构造函数，请勿使用
     */
    private Demo_SayHello() {
        super("demo.sayHello", SecurityType.None);
    }
    
    /**
     * 当前请求有可能的异常返回值
     */
    public int handleError() {
        switch (response.code) {
            /* 用户找不到. 
 en-us:multi-language test 
ja-jp:多言語テスト */
            case ApiCode.DEMO_USER_NOT_FOUND_1000001: {
                break;
            }
        }
        return response.code;
    }

    /**
     * 不要直接调用这个方法，API使用者应该访问基类的getResponse()获取接口的返回值
     */
    @Override
    protected Api_DEMO_DemoEntity getResult(JsonObject json) {
        try {
            return Api_DEMO_DemoEntity.deserialize(json);
        } catch (Exception e) {
            logger.error("Api_DEMO_DemoEntity deserialize failed.", e);
        }
        return null;
    }
    
}
  