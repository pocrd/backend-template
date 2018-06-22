// Auto Generated.  DO NOT EDIT!

package net.pocrd.m.app.client.api.request;

import com.google.gson.JsonObject;
import net.pocrd.m.app.client.BaseRequest;
import net.pocrd.m.app.client.LocalException;
import net.pocrd.m.app.client.SecurityType;
import net.pocrd.m.app.client.api.resp.Api_NumberResp;

/**
 * testSecurityTypeRegisteredDevice
 * 
 * @author guangkaiqiang521
 *
 */
public class Apitest_TestRegisteredDevice extends BaseRequest<Api_NumberResp> {
      
    /**
     * 当前请求的构造函数，以下参数为该请求的必填参数
     * @param intParam int param
     */
    public Apitest_TestRegisteredDevice(int intParam) {
        super("apitest.testRegisteredDevice", SecurityType.RegisteredDevice);

        try {
            params.put("intParam", String.valueOf(intParam));
        } catch(Exception e) {
            throw new LocalException("SERIALIZE_ERROR", LocalException.SERIALIZE_ERROR, e);
        }
    }
    
    /**
     * 私有的默认构造函数，请勿使用
     */
    private Apitest_TestRegisteredDevice() {
        super("apitest.testRegisteredDevice", SecurityType.RegisteredDevice);
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
    protected Api_NumberResp getResult(JsonObject json) {
        try {
            return Api_NumberResp.deserialize(json);
        } catch (Exception e) {
            logger.error("Api_NumberResp deserialize failed.", e);
        }
        return null;
    }
    
}
  