// Auto Generated.  DO NOT EDIT!

package net.pocrd.m.app.client.api.request;

import com.google.gson.JsonObject;
import net.pocrd.m.app.client.BaseRequest;
import net.pocrd.m.app.client.SecurityType;
import net.pocrd.m.app.client.api.resp.Api_StringResp;

/**
 * 测试test testThrowServiceException
 * 
 * @author rendong
 *
 */
public class Apitest_TestThrowServiceException extends BaseRequest<Api_StringResp> {
      
    /**
     * 当前请求的构造函数，以下参数为该请求的必填参数
     */
    public Apitest_TestThrowServiceException() {
        super("apitest.testThrowServiceException", SecurityType.None);
    }
    
    /**
     * 当前请求有可能的异常返回值
     */
    public int handleError() {
        switch (response.code) {
            /* for测试 */
            case ApiCode.TEST_FOR_TEST123_123: {
                break;
            }
        }
        return response.code;
    }

    /**
     * 不要直接调用这个方法，API使用者应该访问基类的getResponse()获取接口的返回值
     */
    @Override
    protected Api_StringResp getResult(JsonObject json) {
        try {
            return Api_StringResp.deserialize(json);
        } catch (Exception e) {
            logger.error("Api_StringResp deserialize failed.", e);
        }
        return null;
    }
    
}
  