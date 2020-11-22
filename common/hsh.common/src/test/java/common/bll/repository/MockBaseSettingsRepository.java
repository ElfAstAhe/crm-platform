package common.bll.repository;

import java.util.HashMap;
import java.util.Map;

public class MockBaseSettingsRepository extends BaseSettingsRepository<MockSettingsEnum>{
    public static final String EXPECTED_DATA_1 = "TestData";
    public static final Integer EXPECTED_DATA_2 = 54321;

    public static final String EXPECTED_DEFAULT_DATA_1 = "Test";
    public static final Integer EXPECTED_DEFAULT_DATA_2 = 12345;

    private final Map<MockSettingsEnum, Object> data;
    private boolean returnNulls = false;

    {
        data = new HashMap<>();
        data.put(MockSettingsEnum.DUMMY1, EXPECTED_DATA_1);
        data.put(MockSettingsEnum.DUMMY2, EXPECTED_DATA_2);
    }

    public boolean isReturnNulls() {
        return returnNulls;
    }

    public void setReturnNulls(boolean returnNulls) {
        this.returnNulls = returnNulls;
    }

    @Override
    protected String getFromSource(MockSettingsEnum setting) {
        if (returnNulls)
            return null;
        return String.valueOf(data.getOrDefault(setting, setting.getDefaultValue()));
    }

    @Override
    protected void setToSource(MockSettingsEnum setting, String value) {
        data.put(setting, value);
    }
}
