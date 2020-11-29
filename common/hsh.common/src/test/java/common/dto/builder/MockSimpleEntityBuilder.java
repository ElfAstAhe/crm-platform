package common.dto.builder;

import test.MockSimpleEntity;

public class MockSimpleEntityBuilder extends BaseBuilder<MockSimpleEntity> {
    public MockSimpleEntityBuilder() {
        super(MockSimpleEntity.class);
    }

    public MockSimpleEntityBuilder setId(Long id) {
        getInstance().setId(id);
        return this;
    }

    public MockSimpleEntityBuilder setName(String name) {
        getInstance().setName(name);
        return this;
    }
}
