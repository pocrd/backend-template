package net.pocrd.demo.service.http;

import net.pocrd.demo.api.DemoService;
import net.pocrd.demo.api.DemoThirdPartyService;
import net.pocrd.demo.dao.dto.DemoDTO;
import net.pocrd.demo.dao.mapper.DemoMapper;
import net.pocrd.demo.entity.DemoEntity;
import net.pocrd.demo.entity.DemoEnum;
import net.pocrd.demo.entity.DemoReturnCode;
import net.pocrd.define.Evaluater;
import net.pocrd.dubboext.DubboExtProperty;
import net.pocrd.entity.ApiReturnCode;
import net.pocrd.entity.ServiceException;
import net.pocrd.entity.ServiceRuntimeException;
import net.pocrd.util.EvaluaterProvider;
import net.pocrd.util.RawString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
    public DemoEntity sayHello(String name) throws ServiceException {
        DemoEntity result = null;
        try {
            result = new DemoEntity();
            DemoDTO demoDTO = demoMapper.queryEntity(name);
            demoDTO.setId(demoThirdPartyService.testThirdParty(demoDTO.getId()));
            evaluater.evaluate(result, demoDTO);
        } catch (ServiceRuntimeException sre) {
            logger.error("api failed.", sre);
            throw new ServiceException("api failed.", sre);
        } catch (Throwable t) {
            logger.error("api failed.", t);
            if (t instanceof ServiceException) {
                throw (ServiceException)t;
            } else {
                throw new ServiceException(DemoReturnCode.DEMO_UNKNOW_ERROR, "api failed.");
            }
        }
        return result;
    }

    @Override
    public DemoEntity testMock(String name) throws ServiceException {
        return null;
    }

    @Override
    public DemoEntity testShortCircuit(String name) throws ServiceException {
        return null;
    }

    @Override
    public DemoEntity tryError(String in) throws ServiceException {
        throw new ServiceException(ApiReturnCode.API_UPGRADE, "try error!");
    }

    @Override
    public String testRegistedDevice() throws ServiceException {
        return "testRegistedDevice";
    }

    @Override
    public String testUserLogin(long deviceId, long userId) throws ServiceException {
        System.out.println("deviceId:" + deviceId + ", userId:" + userId);
        return "deviceId:" + deviceId + ", userId:" + userId;
    }

    @Override
    public String getResByThirdPartyId(String something) throws ServiceException {
        System.out.println("something:" + something);
        return something;
    }

    @Override
    public RawString testRedirect(DemoEnum something, String other) throws ServiceException {
        DubboExtProperty.setRedirectUrl("http://www.fengqu.com/info.api");
        return new RawString("testRedirect");
    }

    @Override
    public String testIgnoreParameterForSecurity(String something) throws ServiceException {
        System.out.println("something:" + something);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
