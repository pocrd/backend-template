package net.pocrd.demo.service.http;

import net.pocrd.define.Evaluater;
import net.pocrd.demo.api.DemoService;
import net.pocrd.demo.api.DemoThirdPartyService;
import net.pocrd.demo.dao.dto.DemoDTO;
import net.pocrd.demo.dao.mapper.DemoMapper;
import net.pocrd.demo.entity.*;
import net.pocrd.dubboext.DubboExtProperty;
import net.pocrd.dubboext.TraceInfo;
import net.pocrd.entity.ApiReturnCode;
import net.pocrd.entity.ServiceException;
import net.pocrd.entity.ServiceRuntimeException;
import net.pocrd.util.EvaluaterProvider;
import net.pocrd.util.RawString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DemoServiceImpl implements DemoService {
    private static final Logger    logger    = LoggerFactory.getLogger(DemoServiceImpl.class);
    private static final Evaluater evaluater = EvaluaterProvider.getEvaluater(DemoEntity.class, DemoDTO.class);
    @Autowired
    private DemoMapper            demoMapper;
    @Autowired
    private DemoThirdPartyService demoThirdPartyService;

    /**
     * 边界异常处理
     *
     * @param name
     *
     * @throws ServiceException
     */
    @Override
    public DemoEntity sayHello(String name) {
        DemoEntity result = null;
        result = new DemoEntity();
        DemoDTO demoDTO = demoMapper.queryEntity(name);
        if (demoDTO == null) {
            throw new ServiceRuntimeException(DemoReturnCode.DEMO_USER_NOT_FOUND, "找不到用户" + name);
        }
        logger.info("say hello to " + name);
        demoDTO.setId(demoThirdPartyService.testThirdParty(demoDTO.getId()));
        evaluater.evaluate(result, demoDTO);
        return result;
    }

    @Override
    public DemoEntity testMock(String name) {
        return null;
    }

    @Override
    public DemoEntity testShortCircuit(String name) {
        return null;
    }

    @Override
    public DemoEntity tryError(String in) {
        throw new ServiceRuntimeException(ApiReturnCode.API_UPGRADE, "try error!");
    }

    @Override
    public String testRegistedDevice() {
        return "testRegistedDevice";
    }

    @Override
    public String testUserLogin() {
        TraceInfo info = TraceInfo.getTraceInfo();
        return info.userinfo;
    }

    @Override
    public String getResByThirdPartyId(String thirdPartyId, String something) {
        System.out.println("something:" + something);
        return something;
    }

    @Override
    public RawString testRedirect(DemoEnum something, String other) {
        DubboExtProperty.setRedirectUrl("http://www.fengqu.com/info.api");
        return new RawString("testRedirect");
    }

    @Override
    public String testIgnoreParameterForSecurity(String something) {
        System.out.println("something:" + something);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Test for service injection
     */
    @Override
    public ComplexTestEntity testApiInjectionR1(String userIds, String productIds, String name) {
        return null;
    }

    @Override
    public ComplexTestEntity testApiInjectionR2(String orderIds, String name) {
        return null;
    }

    @Override
    public ComplexTestEntity testApiInjectionR3(String rmaIds, String name) {
        return null;
    }

    @Override
    public ComplexTestEntity testApiInjectionR4(String name) {
        return null;
    }

    @Override
    public ComplexTestEntity testApiInjectionR5(String name) {
        return null;
    }

    @Override
    public ComplexTestEntity testApiInjectionR6(String rmaIds, String name) {
        return null;
    }

    @Override
    public ComplexTestEntity testApiInjectionR7(String name) {
        return null;
    }

    @Override
    public ComplexTestEntity testApiInjectionR8(String supplierIds, String activityIds, String name) {
        return null;
    }

    private ComplexTestEntity newComplexTestEntity() {
        ComplexTestEntity complexTestEntity = new ComplexTestEntity();
        complexTestEntity.boolValue = true;
        complexTestEntity.byteValue = 1;
        complexTestEntity.charValue = 'a';
        complexTestEntity.doubleValue = 1.0;
        complexTestEntity.floatValue = 1;
        complexTestEntity.shortValue = 1;
        complexTestEntity.intValue = 1;
        complexTestEntity.longValue = 1L;
        SimpleTestEntity simpleTestEntity1 = new SimpleTestEntity();
        simpleTestEntity1.strValue = "simple test entity1";
        complexTestEntity.simpleTestEntity = simpleTestEntity1;
        List<SimpleTestEntity> simpleTestEntityList = new ArrayList<SimpleTestEntity>();
        SimpleTestEntity simpleTestEntity2 = new SimpleTestEntity();
        simpleTestEntity2.strValue = "simple test entity2";
        simpleTestEntityList.add(simpleTestEntity2);
        complexTestEntity.simpleTestEntityList = simpleTestEntityList;

        return complexTestEntity;
    }
}
