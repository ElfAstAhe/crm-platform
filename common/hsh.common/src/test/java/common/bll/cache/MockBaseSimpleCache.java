package common.bll.cache;

import test.MockDtoEntity;

public class MockBaseSimpleCache extends BaseSimpleCache<Integer, MockDtoEntity> {

    @Override
    public Integer buildKey(MockDtoEntity value) {
        return value.getId();
    }
}