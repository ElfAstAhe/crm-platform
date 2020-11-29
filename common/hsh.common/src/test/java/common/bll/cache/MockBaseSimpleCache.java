package common.bll.cache;

import test.MockSimpleEntity;

public class MockBaseSimpleCache extends BaseSimpleCache<Long, MockSimpleEntity> {

    @Override
    public Long buildKey(MockSimpleEntity value) {
        return value.getId();
    }
}
