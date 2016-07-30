package net.pocrd.demo.service.test;

import net.pocrd.demo.api.DemoMockService;
import net.pocrd.demo.api.DemoService;
import net.pocrd.demo.mock.MockServiceImpl;
import net.pocrd.demo.service.http.DemoServiceImpl;
import net.pocrd.core.ApiManager;
import net.pocrd.core.generator.ApiSdkJavaGenerator;
import net.pocrd.entity.ApiMethodInfo;
import org.junit.Test;

import java.util.List;

/**
 * Created by guankaiqiang521 on 2014/9/29.
 */
public class ApiSdkJavaGeneratorTest {
    @Test
    public void testJavaGenertor() {
        List<ApiMethodInfo> infoList = ApiManager.parseApi(DemoService.class, new DemoServiceImpl());
        infoList.addAll(ApiManager.parseApi(DemoMockService.class, new MockServiceImpl()));

        new ApiSdkJavaGenerator.Builder().setPackagePrefix("net.pocrd.m.app.client")
                .setOutputPath("/Users/rendong/workspace/service/dubbo-service-template/autotest/src/main/java/com/fengqu/m/app/client")
                .build().generateViaApiMethodInfo(infoList);
    }
}
