package net.pocrd.demo.mock;

import net.pocrd.define.MockApiImplementation;
import net.pocrd.demo.api.DemoMockService;
import net.pocrd.demo.entity.DemoEntity;

/**
 * Created by rendong on 15/8/5.
 */
public class MockServiceImpl implements MockApiImplementation<DemoMockService>, DemoMockService {

    @Override
    public DemoEntity testMockService(String name) {
        DemoEntity entity = new DemoEntity();
        entity.id = 7654321;
        entity.name = "mock service test " + name;
        return entity;
    }

    @Override
    public void $setProxy(DemoMockService proxy) {

    }
}
