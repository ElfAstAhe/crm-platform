package common.bll.cache;

import test.TestSerializeClass;

public class TestBaseSimpleCache extends BaseSimpleCache<Integer, TestSerializeClass> {

    public Integer buildKey(TestSerializeClass value) {
        return value.getId();
    }
}
