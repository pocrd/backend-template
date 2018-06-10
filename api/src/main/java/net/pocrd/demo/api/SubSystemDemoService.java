package net.pocrd.demo.api;

import net.pocrd.annotation.ApiGroup;
import net.pocrd.annotation.HttpApi;
import net.pocrd.annotation.SubSystem;
import net.pocrd.define.AuthenticationService;
import net.pocrd.define.SecurityType;
import net.pocrd.demo.entity.DemoReturnCode;
import net.pocrd.responseEntity.AuthenticationResult;

/**
 * Created by rendong on 2018/4/10.
 */
@ApiGroup(name = "demo", minCode = 1000000, maxCode = 2000000, codeDefine = DemoReturnCode.class, owner = "demo")
public interface SubSystemDemoService extends AuthenticationService {
    @SubSystem(1)
    @HttpApi(name = "subSystemDemo.authenticate", desc = "子系统授权demo", security = SecurityType.SubSystemUser, owner = "demo")
    AuthenticationResult authenticate(long userid, String authType, String authInfo);
}
