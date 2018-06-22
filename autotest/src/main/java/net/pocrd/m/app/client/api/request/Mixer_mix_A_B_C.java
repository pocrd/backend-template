// Auto Generated.  DO NOT EDIT!

package net.pocrd.m.app.client.api.request;

import com.google.gson.JsonObject;
import net.pocrd.m.app.client.BaseRequest;
import net.pocrd.m.app.client.SecurityType;
import net.pocrd.m.app.client.api.resp.Api_MIXER_MixDemo_Result;

/**
 * demo for data mixer page url:/index.html
 *
 * @author pocrd
 *
 */
public class Mixer_Mix_A_B_C extends BaseRequest<Api_MIXER_MixDemo_Result> {

    private BaseRequest[] dependencies = null;

    protected BaseRequest[] getDependencies() {
        return dependencies;
    }

    /**
     * 当前mix请求的构造函数, 服务端匹配"参数接口返回值类型和本接口参数类型"的成员变量名以及
     * 类型来进行赋值。请详细查询文档后根据输入参数类型结构决定用哪个接口的返回值作为参数。
     * @param arg0 待合并返回值, 请使用返回值类型结构与Api_MIXER_MixDemo_DemoEntity相似的接口作为参数
     * @param arg1 待合并返回值, 请使用返回值类型结构与Api_MIXER_MixDemo_ComplexTestEntity相似的接口作为参数
     * @param arg2 待合并返回值, 请使用返回值类型结构与Api_MIXER_MixDemo_SimpleTestEntity相似的接口作为参数
     */
    public Mixer_Mix_A_B_C(BaseRequest arg0, BaseRequest arg1, BaseRequest arg2) {
        super("mixer.mix_A_B_C", SecurityType.None);

        dependencies = new BaseRequest[3];
        dependencies[0] = arg0;
        dependencies[1] = arg1;
        dependencies[2] = arg2;
    }

    /**
     * 私有的默认构造函数，请勿使用
     */
    private Mixer_Mix_A_B_C() {
        super("mixer.mix_A_B_C", SecurityType.None);
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
    protected Api_MIXER_MixDemo_Result getResult(JsonObject json) {
        try {
            return Api_MIXER_MixDemo_Result.deserialize(json);
        } catch (Exception e) {
            logger.error("Api_MIXER_MixDemo_Result deserialize failed.", e);
        }
        return null;
    }

}
  