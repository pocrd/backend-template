package net.pocrd.demo.api;

import net.pocrd.demo.entity.DemoEntity;
import net.pocrd.demo.entity.DemoEnum;
import net.pocrd.demo.entity.DemoReturnCode;
import net.pocrd.demo.mock.MockDemoEntity;
import net.pocrd.annotation.*;
import net.pocrd.define.CommonParameter;
import net.pocrd.define.SecurityType;
import net.pocrd.entity.ServiceException;
import net.pocrd.util.RawString;

@ApiGroup(name = "demo", minCode = 1, maxCode = 1000000, codeDefine = DemoReturnCode.class, owner = "demo")
public interface DemoService {
    @HttpApi(name = "demo.sayHello", desc = "demo test多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト",
             security = SecurityType.None, owner = "demo")
    public DemoEntity sayHello(
            @ApiParameter(required = true, name = "name", desc = "say hello多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト")
            String name) throws ServiceException;

    @HttpApi(name = "demo.testMock", desc = "mock test多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト",
             security = SecurityType.None, owner = "demo")
    @ApiMockReturnObject(MockDemoEntity.class)
    public DemoEntity testMock(
            @ApiParameter(required = true, name = "name", desc = "say hello多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト")
            String name) throws ServiceException;

    @HttpApi(name = "demo.testShortCircuit", desc = "short circuit test多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト",
             security = SecurityType.None, owner = "demo")
    @ApiShortCircuit(MockDemoEntity.class)
    public DemoEntity testShortCircuit(
            @ApiParameter(required = true, name = "name", desc = "say hello多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト")
            String name) throws ServiceException;

    @HttpApi(name = "demo.tryError", desc = "demo error多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト",
             security = SecurityType.None, owner = "demo")
    public DemoEntity tryError(
            @ApiParameter(required = true, name = "in", desc = "input param多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト")
            String in) throws ServiceException;

    @HttpApi(name = "demo.testRegistedDevice", desc = "demo registed device多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト",
             security = SecurityType.RegisteredDevice, owner = "demo")
    @DesignedErrorCode(DemoReturnCode._C_DEMO_DEVICE_DENIED)
    public String testRegistedDevice() throws ServiceException;

    @HttpApi(name = "demo.testUserLogin", desc = "demo user login api多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト",
             security = SecurityType.UserLogin, owner = "demo")
    public String testUserLogin(
            @ApiAutowired(CommonParameter.deviceId)
            long deviceId,
            @ApiAutowired(CommonParameter.userId)
            long userId) throws ServiceException;

    @HttpApi(name = "demo.getResByThirdPartyId", desc = "demo getResByThirdPartyId多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト",
             security = SecurityType.Integrated, owner = "demo",
             allowThirdPartyIds = { 1 })
    public String getResByThirdPartyId(
            @ApiParameter(required = true, name = "in", desc = "输入参数多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト")
            String something) throws ServiceException;

    @HttpApi(name = "demo.testRedirect", desc = "test redirect多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト",
             security = SecurityType.None, owner = "demo")
    public RawString testRedirect(
            @ApiParameter(required = true, name = "something", desc = "输入参数1多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト")
            DemoEnum something,
            @EnumDef(DemoEnum.class)
            @ApiParameter(required = true, name = "another", desc = "输入参数2多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト")
            String another
    ) throws ServiceException;

    @HttpApi(name = "demo.testIgnoreParameterForSecurity", desc = "test redirect多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト",
             security = SecurityType.None, owner = "demo")
    public String testIgnoreParameterForSecurity(
            @ApiParameter(required = true, name = "in", desc = "输入参数多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト", ignoreForSecurity = true)
            String something
    ) throws ServiceException;
}