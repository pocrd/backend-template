package net.pocrd.demo.service;

import net.pocrd.demo.api.DemoThirdPartyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by guankaiqiang521 on 2014/10/22.
 */
public class DemoThirdPartyServiceImpl implements DemoThirdPartyService {
    private static final Logger logger = LoggerFactory.getLogger(DemoThirdPartyServiceImpl.class);

    @Override
    public int testThirdParty(int id) {
        logger.info("id:{}", id);
        return id;
    }
}
