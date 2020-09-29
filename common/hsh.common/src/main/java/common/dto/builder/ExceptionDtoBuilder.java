package common.dto.builder;

import common.dto.ExceptionDto;

public class ExceptionDtoBuilder extends BaseBuilder<ExceptionDto> {
    private ExceptionDtoBuilder() {
        super(ExceptionDto.class);
    }

    public static ExceptionDtoBuilder get() {
        return new ExceptionDtoBuilder();
    }

    public ExceptionDtoBuilder setException(String exception) {
        getInstance().setException(exception);
        return this;
    }

    public ExceptionDtoBuilder setMessage(String message) {
        getInstance().setMessage(message);
        return this;
    }

    public ExceptionDtoBuilder setStackTrace(String stackTrace) {
        getInstance().setStackTrace(stackTrace);
        return this;
    }
}
