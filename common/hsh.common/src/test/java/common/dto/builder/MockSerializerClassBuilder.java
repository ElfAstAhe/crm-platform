package common.dto.builder;

import test.MockDtoEntity;

public class MockSerializerClassBuilder extends BaseBuilder<MockDtoEntity> {
    public MockSerializerClassBuilder() {
        super(MockDtoEntity.class);
    }

    public MockSerializerClassBuilder setId(Integer id) {
        getInstance().setId(id);
        return this;
    }

    public MockSerializerClassBuilder setName(String name) {
        getInstance().setName(name);
        return this;
    }
}
