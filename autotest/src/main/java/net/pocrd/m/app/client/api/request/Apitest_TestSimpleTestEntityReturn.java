// Auto Generated.  DO NOT EDIT!

package net.pocrd.m.app.client.api.request;

import com.google.gson.JsonObject;
import net.pocrd.m.app.client.BaseRequest;
import net.pocrd.m.app.client.LocalException;
import net.pocrd.m.app.client.SecurityType;
import net.pocrd.m.app.client.api.resp.Api_APITEST_SimpleTestEntity;

/**
 * testSimpleTestEntityReturn
 * 
 * @author rendong
 *
 */
public class Apitest_TestSimpleTestEntityReturn extends BaseRequest<Api_APITEST_SimpleTestEntity> {
      
    /**
     * 当前请求的构造函数，以下参数为该请求的必填参数
     * @param strParam string param
     */
    public Apitest_TestSimpleTestEntityReturn(String strParam) {
        super("apitest.testSimpleTestEntityReturn", SecurityType.None);

        try {
            params.put("strParam", strParam);
        } catch(Exception e) {
            throw new LocalException("SERIALIZE_ERROR", LocalException.SERIALIZE_ERROR, e);
        }
    }
    
    /**
     * 私有的默认构造函数，请勿使用
     */
    private Apitest_TestSimpleTestEntityReturn() {
        super("apitest.testSimpleTestEntityReturn", SecurityType.None);
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
    protected Api_APITEST_SimpleTestEntity getResult(JsonObject json) {
        try {
            return Api_APITEST_SimpleTestEntity.deserialize(json);
        } catch (Exception e) {
            logger.error("Api_APITEST_SimpleTestEntity deserialize failed.", e);
        }
        return null;
    }
    
}
  