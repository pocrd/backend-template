package net.pocrd.demo.api;

import net.pocrd.annotation.*;
import net.pocrd.define.AutowireableParameter;
import net.pocrd.define.SecurityType;
import net.pocrd.demo.entity.ComplexTestEntity;
import net.pocrd.demo.entity.DemoEntity;
import net.pocrd.demo.entity.DemoEnum;
import net.pocrd.demo.entity.DemoReturnCode;
import net.pocrd.demo.mock.MockDemoEntity;
import net.pocrd.entity.LongArrayStringInjector;
import net.pocrd.util.RawString;

@ApiGroup(name = "demo", minCode = 1000000, maxCode = 2000000, codeDefine = DemoReturnCode.class, owner = "demo")
public interface DemoService {
    @HttpApi(name = "demo.sayHello", desc = "demo test多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト",
             security = SecurityType.None, owner = "demo")
    @DesignedErrorCode(DemoReturnCode._C_DEMO_USER_NOT_FOUND)
    DemoEntity sayHello(
            @ApiParameter(required = true, name = "name", desc = "say hello多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト") String name);

    @HttpApi(name = "demo.testMock", desc = "mock test多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト",
             security = SecurityType.None, owner = "demo")
    @ApiMockReturnObject(MockDemoEntity.class)
    DemoEntity testMock(
            @ApiParameter(required = true, name = "name", desc = "say hello多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト") String name);

    @HttpApi(name = "demo.testShortCircuit", desc = "short circuit test多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト",
             security = SecurityType.None, owner = "demo")
    @ApiShortCircuit(MockDemoEntity.class)
    DemoEntity testShortCircuit(
            @ApiParameter(required = true, name = "name", desc = "say hello多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト") String name);

    @HttpApi(name = "demo.tryError", desc = "demo error多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト",
             security = SecurityType.None, owner = "demo")
    DemoEntity tryError(
            @ApiParameter(required = true, name = "in", desc = "input param多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト") String in);

    @HttpApi(name = "demo.testRegistedDevice", desc = "demo registed device多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト",
             security = SecurityType.RegisteredDevice, owner = "demo")
    @DesignedErrorCode(DemoReturnCode._C_DEMO_SOMETHING_WRONG)
    String testRegistedDevice();

    @HttpApi(name = "demo.testUserLogin", desc = "demo user login api多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト",
             security = SecurityType.UserLogin, owner = "demo")
    String testUserLogin();

    @HttpApi(name = "demo.getResByThirdPartyId", desc = "demo getResByThirdPartyId多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト",
             security = SecurityType.Integrated, owner = "demo")
    String getResByThirdPartyId(
            @ApiAutowired(AutowireableParameter.thirdPartyId) String thirdPartyId,
            @ApiParameter(required = true, name = "in", desc = "输入参数多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト") String something);

    @HttpApi(name = "demo.testRedirect", desc = "test redirect多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト",
             security = SecurityType.None, owner = "demo")
    RawString testRedirect(
            @ApiParameter(required = true, name = "something", desc = "输入参数1多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト") DemoEnum something,
            @EnumDef(DemoEnum.class)
            @ApiParameter(required = true, name = "another", desc = "输入参数2多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト") String another
    );

    @HttpApi(name = "demo.testIgnoreParameterForSecurity", desc = "test redirect多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト", security = SecurityType.None, owner = "demo")
    String testIgnoreParameterForSecurity(
            @ApiParameter(required = true, name = "in", desc = "输入参数多语言测试. \nen-us:multi-language test \nja-jp:多言語テスト", ignoreForSecurity = true) String something
    );

    class UserIdInjector extends LongArrayStringInjector {

        @Override
        public String getName() {
            return "user.userIds";
        }
    }

    class ProductIdInjector extends LongArrayStringInjector {

        @Override
        public String getName() {
            return "product.productIds";
        }
    }

    class OrderIdInjector extends LongArrayStringInjector {

        @Override
        public String getName() {
            return "order.orderIds";
        }
    }

    class RMAIdInjector extends LongArrayStringInjector {

        @Override
        public String getName() {
            return "rma.rmaIds";
        }
    }

    class RMAUserIdInjector extends LongArrayStringInjector {

        @Override
        public String getName() {
            return "rma.userIds";
        }
    }

    class SupplierIdInjector extends LongArrayStringInjector {

        @Override
        public String getName() {
            return "supplier.supplierIds";
        }
    }

    class ActivityIdInjector extends LongArrayStringInjector {

        @Override
        public String getName() {
            return "cms.activityIds";
        }
    }

    // _mt=r1(r2/r3@1),r2(r4),r3@1(r5),r3@2(r5),r4,r5,r6(r5),r7,r8(r6/r7)

    @HttpApi(name = "demo.testApiInjectionR1", desc = "可以依赖与R2,R3的隐式返回值", security = SecurityType.None, owner = "demo")
    ComplexTestEntity testApiInjectionR1(
            @ApiParameter(required = true, name = "userIds", desc = "以半角逗号分隔的用户id列表", serviceInject = UserIdInjector.class)
                    String userIds,
            @ApiParameter(required = false, name = "productIds", desc = "以半角逗号分隔的商品id列表", serviceInject = ProductIdInjector.class)
                    String productIds,
            @ApiParameter(required = true, name = "name", desc = "名称")
                    String name
    );

    @HttpApi(name = "demo.testApiInjectionR2", desc = "可以依赖与R4的隐式返回值, 并提供R1所需的一个隐式参数", security = SecurityType.None, owner = "demo")
    @ParamExport("user.userIds")
    ComplexTestEntity testApiInjectionR2(
            @ApiParameter(required = true, name = "orderIds", desc = "以半角逗号分隔的订单id列表", serviceInject = OrderIdInjector.class)
                    String orderIds,
            @ApiParameter(required = false, name = "name", desc = "名称")
                    String name
    );

    @HttpApi(name = "demo.testApiInjectionR3", desc = "可以依赖与R5的隐式返回值, 并提供R1所需的一个隐式参数", security = SecurityType.None, owner = "demo")
    @ParamExport("product.productIds")
    ComplexTestEntity testApiInjectionR3(
            @ApiParameter(required = false, name = "rmaIds", desc = "以半角逗号分隔的售后id列表", serviceInject = RMAIdInjector.class)
                    String rmaIds,
            @ApiParameter(required = true, name = "name", desc = "名称")
                    String name
    );

    @HttpApi(name = "demo.testApiInjectionR4", desc = "提供R2所需的隐式参数", security = SecurityType.None, owner = "demo")
    @ParamExport("order.orderIds")
    ComplexTestEntity testApiInjectionR4(
            @ApiParameter(required = false, name = "name", desc = "名称")
                    String name
    );

    @HttpApi(name = "demo.testApiInjectionR5", desc = "提供R3,R6所需的一个隐式参数", security = SecurityType.None, owner = "demo")
    @ParamExport({ "rma.rmaIds", "rma.userIds" })
    ComplexTestEntity testApiInjectionR5(
            @ApiParameter(required = true, name = "name", desc = "名称")
                    String name
    );

    @HttpApi(name = "demo.testApiInjectionR6", desc = "可以依赖与R5的隐式返回值, 并提供R8所需的一个隐式参数", security = SecurityType.None, owner = "demo")
    @ParamExport("supplier.supplierIds")
    ComplexTestEntity testApiInjectionR6(
            @ApiParameter(required = true, name = "userIds", desc = "以半角逗号分隔的售后id列表", serviceInject = RMAUserIdInjector.class)
                    String userIds,
            @ApiParameter(required = false, name = "name", desc = "名称")
                    String name
    );

    @HttpApi(name = "demo.testApiInjectionR7", desc = "提供R8所需的一个隐式参数", security = SecurityType.None, owner = "demo")
    @ParamExport("cms.activityIds")
    ComplexTestEntity testApiInjectionR7(
            @ApiParameter(required = false, name = "name", desc = "名称")
                    String name
    );

    @HttpApi(name = "demo.testApiInjectionR8", desc = "可以依赖与R6,R7的隐式返回值", security = SecurityType.None, owner = "demo")
    ComplexTestEntity testApiInjectionR8(
            @ApiParameter(required = true, name = "supplierIds", desc = "以半角逗号分隔的供应商id列表", serviceInject = SupplierIdInjector.class)
                    String supplierIds,
            @ApiParameter(required = false, name = "activityIds", desc = "以半角逗号分隔的活动页id列表", serviceInject = ActivityIdInjector.class)
                    String activityIds,
            @ApiParameter(required = true, name = "name", desc = "名称")
                    String name
    );
}