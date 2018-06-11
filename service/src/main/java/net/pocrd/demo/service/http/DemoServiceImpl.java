package net.pocrd.demo.service.http;

import net.pocrd.define.Evaluator;
import net.pocrd.define.LongArrayStringInjector;
import net.pocrd.demo.api.DemoService;
import net.pocrd.demo.api.DemoThirdPartyService;
import net.pocrd.demo.dao.dto.DemoDTO;
import net.pocrd.demo.dao.mapper.DemoMapper;
import net.pocrd.demo.entity.ComplexTestEntity;
import net.pocrd.demo.entity.DemoEntity;
import net.pocrd.demo.entity.DemoReturnCode;
import net.pocrd.demo.entity.SimpleTestEntity;
import net.pocrd.dubboext.DubboExtProperty;
import net.pocrd.dubboext.TraceInfo;
import net.pocrd.entity.ApiReturnCode;
import net.pocrd.entity.ServiceException;
import net.pocrd.entity.ServiceRuntimeException;
import net.pocrd.responseEntity.DynamicEntity;
import net.pocrd.responseEntity.KeyValueList;
import net.pocrd.responseEntity.KeyValuePair;
import net.pocrd.responseEntity.RawString;
import net.pocrd.util.EvaluatorProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DemoServiceImpl implements DemoService {
    private static final Logger    logger    = LoggerFactory.getLogger(DemoServiceImpl.class);
    private static final Evaluator evaluater = EvaluatorProvider.getEvaluator(DemoEntity.class, DemoDTO.class);
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
    public RawString testRedirect(String something, String other) {
        DubboExtProperty.setRedirectUrl("http://www.pocrd.com/info.api");
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
        ComplexTestEntity entity = newComplexTestEntity();
        entity.strValue += "   userIds:" + userIds + "   productIds:" + productIds + "   name:" + name;
        return entity;
    }

    @Override
    public ComplexTestEntity testApiInjectionR2(String orderIds, String name) {
        ComplexTestEntity entity = newComplexTestEntity();
        entity.strValue += "   orderIds:" + orderIds + "   name:" + name;
        DubboExtProperty.exportServiceData(new LongArrayStringInjector.Data("user.userIds", "123,456,789"));
        return entity;
    }

    @Override
    public ComplexTestEntity testApiInjectionR3(String rmaIds, String name) {
        ComplexTestEntity entity = newComplexTestEntity();
        entity.strValue += "   rmaIds:" + rmaIds + "   name:" + name;
        DubboExtProperty.exportServiceData(new LongArrayStringInjector.Data("product.productIds", "234,567,891"));
        return entity;
    }

    @Override
    public ComplexTestEntity testApiInjectionR4(String name) {
        ComplexTestEntity entity = newComplexTestEntity();
        entity.strValue += "   name:" + name;
        DubboExtProperty.exportServiceData(new LongArrayStringInjector.Data("order.orderIds", "345,678,912"));
        return entity;
    }

    @Override
    public ComplexTestEntity testApiInjectionR5(String name) {
        ComplexTestEntity entity = newComplexTestEntity();
        entity.strValue += "   name:" + name;
        DubboExtProperty.exportServiceData(new LongArrayStringInjector.Data("rma.rmaIds", "456,789,123"));
        DubboExtProperty.exportServiceData(new LongArrayStringInjector.Data("rma.userIds", "567,891,234"));
        return entity;
    }

    @Override
    public ComplexTestEntity testApiInjectionR6(String rmaIds, String name) {
        ComplexTestEntity entity = newComplexTestEntity();
        entity.strValue += "   rmaIds:" + rmaIds + "   name:" + name;
        DubboExtProperty.exportServiceData(new LongArrayStringInjector.Data("supplier.supplierIds", "678,912,345"));
        return entity;
    }

    @Override
    public ComplexTestEntity testApiInjectionR7(String name) {
        ComplexTestEntity entity = newComplexTestEntity();
        entity.strValue += "   name:" + name;
        DubboExtProperty.exportServiceData(new LongArrayStringInjector.Data("cms.activityIds", "789,123,456"));
        return entity;
    }

    @Override
    public ComplexTestEntity testApiInjectionR8(String supplierIds, String activityIds, String name) {
        ComplexTestEntity entity = newComplexTestEntity();
        entity.strValue += "   supplierIds:" + supplierIds + "   activityIds:" + activityIds + "   name:" + name;
        return entity;
    }

    private ComplexTestEntity newComplexTestEntity() {
        ComplexTestEntity complexTestEntity = new ComplexTestEntity();
        complexTestEntity.boolValue = true;
        complexTestEntity.byteValue = (byte)(Math.random() * Byte.MAX_VALUE);
        complexTestEntity.charValue = 'x';
        complexTestEntity.doubleValue = Math.random() * 1000;
        complexTestEntity.floatValue = (float)(Math.random() * 1000);
        complexTestEntity.shortValue = (short)(Math.random() * Short.MAX_VALUE);
        complexTestEntity.intValue = (int)(Math.random() * Integer.MAX_VALUE);
        complexTestEntity.longValue = (long)(Math.random() * Long.MAX_VALUE);
        SimpleTestEntity simpleTestEntity1 = new SimpleTestEntity();
        simpleTestEntity1.strValue = "simple test entity1." + Math.random();
        simpleTestEntity1.intArray = new int[] { 7, 6, 5, 4, 3, 2, 1 };
        complexTestEntity.simpleTestEntity = simpleTestEntity1;
        List<SimpleTestEntity> simpleTestEntityList = new ArrayList<SimpleTestEntity>();
        SimpleTestEntity simpleTestEntity2 = new SimpleTestEntity();
        simpleTestEntity2.strValue = "simple test entity2." + Math.random();
        simpleTestEntity2.intArray = new int[] { 1, 2, 3, 4, 5, 6, 7 };
        simpleTestEntityList.add(simpleTestEntity2);
        simpleTestEntityList.add(simpleTestEntity2);
        complexTestEntity.simpleTestEntityList = simpleTestEntityList;
        complexTestEntity.strValue = "complex test entity3." + Math.random();
        KeyValueList ste = new KeyValueList();
        ste.keyValue = new ArrayList<KeyValuePair>(3);
        ste.keyValue.add(new KeyValuePair("a", "b"));
        ste.keyValue.add(new KeyValuePair("c", "d"));
        complexTestEntity.dynamicEntity = new DynamicEntity<KeyValueList>(ste);
        List<DynamicEntity> des = new ArrayList<DynamicEntity>(3);
        {
            SimpleTestEntity s = new SimpleTestEntity();
            s.intArray = new int[] { 4, 1, 4 };
            s.strValue = "kkkkkk";
            DynamicEntity de1 = new DynamicEntity(s);
            des.add(de1);

            KeyValueList kvl = new KeyValueList();
            kvl.keyValue = new ArrayList<KeyValuePair>(3);
            kvl.keyValue.add(new KeyValuePair("x", "y"));
            kvl.keyValue.add(new KeyValuePair("z", "$"));
            DynamicEntity de2 = new DynamicEntity(kvl);
            des.add(de2);
        }
        complexTestEntity.dynamicEntityList = des;

        return complexTestEntity;
    }
}
