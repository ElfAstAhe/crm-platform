package common.dto.helper;

import common.dto.ExceptionDto;
import common.web.MimeTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class ExceptionDtoHelperTest {
    @ParameterizedTest
    @ValueSource(strings = {MimeTypes.Application.XML, MimeTypes.Application.YAML, MimeTypes.Application.JSON})
    public void getCorrectContentType_useContentType_shouldReturnContentType(String contentType) {
        // prepare
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.doReturn(contentType).when(request).getHeader(Mockito.anyString());
        // act
        String actual = ExceptionDtoHelper.getCorrectContentType(request);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(contentType, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"shitData", "<null>"})
    public void getCorrectContentType_useShitDataOrNull_shouldReturnJsonContentType(String contentType) {
        // prepare
        String prepParam = contentType;
        if ("<null>".equals(prepParam))
            prepParam = null;
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.doReturn(prepParam).when(request).getHeader(Mockito.anyString());
        // act
        String actual = ExceptionDtoHelper.getCorrectContentType(request);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(MimeTypes.Application.JSON, actual);
    }

    @Test
    public void toDto_useException_shouldReturnDtoInstance() {
        // prepare
        Exception ex = new Exception();
        // act
        ExceptionDto actual = ExceptionDtoHelper.toDto(ex);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(ex.getClass().getName(), actual.getException());
    }

    @Test
    public void toDto_useNull_shouldReturnNull() {
        // prepare
        // act
        // assert
        Assertions.assertNull(ExceptionDtoHelper.toDto(null));
    }

    @Test
    public void getStackTrace_useException_shouldReturnData() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        // prepare
        Exception ex = new Exception();
        Method meth = ExceptionDtoHelper.class.getDeclaredMethod("getStackTrace", Throwable.class);
        meth.setAccessible(true);
        // act
        String actual = (String) meth.invoke(ExceptionDtoHelper.class, ex);
        // assert
        Assertions.assertNotNull(actual);
    }

    @Test
    public void getStackTrace_useNull_shouldReturnNull() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // prepare
        Exception ex = null;
        Method meth = ExceptionDtoHelper.class.getDeclaredMethod("getStackTrace", Throwable.class);
        meth.setAccessible(true);
        // act
        String actual = (String) meth.invoke(ExceptionDtoHelper.class, ex);
        // assert
        Assertions.assertNull(actual);
    }
}