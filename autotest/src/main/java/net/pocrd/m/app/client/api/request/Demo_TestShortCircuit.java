// Auto Generated.  DO NOT EDIT!

package net.pocrd.m.app.client.api.request;

import com.google.gson.JsonObject;
import net.pocrd.m.app.client.BaseRequest;
import net.pocrd.m.app.client.LocalException;
import net.pocrd.m.app.client.SecurityType;
import net.pocrd.m.app.client.api.resp.Api_DEMO_DemoEntity;

/**
 * short circuit test多语言测试. 
en-us:multi-language test 
ja-jp:多言語テスト
 * 
 * @author demo
 *
 */
public class Demo_TestShortCircuit extends BaseRequest<Api_DEMO_DemoEntity> {
      
    /**
     * 当前请求的构造函数，以下参数为该请求的必填参数
     * @param name say hello多语言测试. 
en-us:multi-language test 
ja-jp:多言語テスト
     */
    public Demo_TestShortCircuit(String name) {
        super("demo.testShortCircuit", SecurityType.None);

        try {
            params.put("name", name);
        } catch(Exception e) {
            throw new LocalException("SERIALIZE_ERROR", LocalException.SERIALIZE_ERROR, e);
        }
    }
    
    /**
     * 私有的默认构造函数，请勿使用
     */
    private Demo_TestShortCircuit() {
        super("demo.testShortCircuit", SecurityType.None);
    }
    
    /**
     * 当前请求有可能的异常返回值
     */
    public int handleError() {
        switch (response.code) {
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
  