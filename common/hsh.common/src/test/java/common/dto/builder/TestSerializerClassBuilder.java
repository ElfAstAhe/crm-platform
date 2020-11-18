package common.dto.builder;

import test.TestSerializeClass;

public class TestSerializerClassBuilder extends BaseBuilder<TestSerializeClass> {
    public TestSerializerClassBuilder() {
        super(TestSerializeClass.class);
    }

    public TestSerializerClassBuilder setId(Integer id) {
        getInstance().setId(id);
        return this;
    }

    public TestSerializerClassBuilder setName(String name) {
        getInstance().setName(name);
        return this;
    }
}
