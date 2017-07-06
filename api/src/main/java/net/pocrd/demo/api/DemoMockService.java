package net.pocrd.demo.api;

import net.pocrd.demo.entity.DemoEntity;
import net.pocrd.demo.entity.DemoReturnCode;
import net.pocrd.demo.mock.MockServiceImpl;
import net.pocrd.annotation.ApiGroup;
import net.pocrd.annotation.ApiMockInterfaceImpl;
import net.pocrd.annotation.ApiParameter;
import net.pocrd.annotation.HttpApi;
import net.pocrd.define.SecurityType;
import net.pocrd.entity.ServiceException;

/**
 * Created by rendong on 15/8/5.
 */
@ApiGroup(name = "demo", minCode = 1, maxCode = 1000000, codeDefine = DemoReturnCode.class, owner = "demo")
@ApiMockInterfaceImpl(MockServiceImpl.class)
public interface DemoMockService {
    @HttpApi(name = "demo.testMockService", desc = "测试模拟服务. \n en-us:mock service test. \n ja-jp:モックサービス・テスト", security = SecurityType.None, owner = "demo")
    public DemoEntity testMockService(
            @ApiParameter(required = true, name = "name", desc = "say hello多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト")
            String name) throws ServiceException;
}