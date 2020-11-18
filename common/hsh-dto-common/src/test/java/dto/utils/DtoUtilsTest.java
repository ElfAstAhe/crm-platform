package dto.utils;

import org.junit.jupiter.api.*;

class DtoUtilsTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void nullOrValue_nullParam_shouldReturnNullRepresentation() {
        // prepare
        String expected = DtoUtils.NULL_STRING;
        // act
        String actual = DtoUtils.nullOrValue(null);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void nullOrValue_nonNullParam_shouldReturnStringRepresentation() {
        // prepare
        Long data = 1L;
        String expected = String.valueOf(data);
        // act
        String actual = DtoUtils.nullOrValue(data);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void buildKeyValue_useParams_shouldReturnCorrectData() {
        // prepare
        String key = "simpleKey";
        Object value = 1L;
        String expected = "simpleKey=[1]";
        // act
        String actual = DtoUtils.buildKeyValue(key, value);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void buildToString_useDto_shouldReturnRepresentation() {
        // prepare
        DtoUtilsTestDto data = buildTestDto();
        String expected = "dto.utils.DtoUtilsTestDto[serialVersionUID=[1],id=[1],name=[Test],someData=[TestData]]";
        // act
        String actual = DtoUtils.buildToString(data);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void buildToString_useNull_shouldReturnNull() {
        // prepare
        DtoUtilsTestDto data = null;
        String expected = null;
        // act
        String actual = DtoUtils.buildToString(null);
        // assert
        Assertions.assertNull(actual);
    }

    private DtoUtilsTestDto buildTestDto() {
        DtoUtilsTestDto result = new DtoUtilsTestDto();
        result.setId(1L);
        result.setName("Test");
        result.setSomeData("TestData");
        return result;
    }
}