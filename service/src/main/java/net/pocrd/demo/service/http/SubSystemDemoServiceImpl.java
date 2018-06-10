package net.pocrd.demo.service.http;

import net.pocrd.responseEntity.AuthenticationResult;

/**
 * Created by rendong on 2018/4/10.
 */
public class SubSystemDemoServiceImpl implements DemoServiceImpl.X, DemoServiceImpl.Y {
    @Override
    public AuthenticationResult authenticate(long userid, String authType, String authInfo) {
        return new AuthenticationResult();
    }
}
