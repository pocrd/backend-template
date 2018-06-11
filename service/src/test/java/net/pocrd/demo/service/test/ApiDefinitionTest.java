package net.pocrd.demo.service.test;

import net.pocrd.core.ApiDocumentationHelper;
import net.pocrd.core.ApiManager;
import net.pocrd.demo.api.DemoService;
import net.pocrd.entity.ApiMethodInfo;
import org.junit.Test;

import java.util.List;

/**
 * Created by guankaiqiang521 on 2014/9/29.
 */
public class ApiDefinitionTest {
    @Test
    public void testApi() {
        ApiManager manager = new ApiManager();
        ApiDocumentationHelper apiDoc = new ApiDocumentationHelper();
        List<ApiMethodInfo> apis = null;
        apis = ApiManager.parseApi(DemoService.class);
        apiDoc.getDocument(apis.toArray(new ApiMethodInfo[apis.size()]));
        manager.register("api definition test", apis, new Object());
        System.out.println(apis.size());
    }
}
