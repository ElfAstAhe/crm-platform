package common.ep.mapper;

import java.util.Arrays;
import java.util.List;

public class ExceptionMapperHelper {
    private ExceptionMapperHelper() {
        // hide constructor
    }

    public static List<Class<?>> buildMapperProviderClassList() {
        return Arrays.asList(
                // 400
                BadRequestExceptionMapper.class,
                // 401
                NotAuthorizedExceptionMapper.class,
                // 402
                PaymentRequiredExceptionMapper.class,
                // 403
                ForbiddenExceptionMapper.class,
                // 404
                NotFoundExceptionMapper.class,
                // 405
                NotAllowedExceptionMapper.class,
                // 406
                NotAcceptableExceptionMapper.class,
                // 407
                ProxyAuthenticationRequiredExceptionMapper.class,
                // 408
                RequestTimeoutExceptionMapper.class,
                // 409
                ConflictExceptionMapper.class,
                // 410
                ResourceGoneExceptionMapper.class,
                // 411
                LengthRequiredExceptionMapper.class,
                // 412
                PreconditionFailedExceptionMapper.class,
                // 413
                RequestEntityTooLargeExceptionMapper.class,
                // 414
                RequestUriTooLongExceptionMapper.class,
                // 415
                NotSupportedExceptionMapper.class,
                // 416
                RequestedRangeNotSatisfiableExceptionMapper.class,
                // 417
                ExpectationFailedExceptionMapper.class,
                // 428
                PreconditionRequiredExceptionMapper.class,
                // 429
                TooManyRequestsExceptionMapper.class,
                // 431
                RequestHeaderFieldsTooLargeExceptionMapper.class,
                // 500
                InternalServerErrorExceptionMapper.class,
                // 501
                NotImplementedExceptionMapper.class,
                // 502
                BadGatewayExceptionMapper.class,
                // 503
                ServiceUnavailableExceptionMapper.class,
                // 504
                GatewayTimeoutExceptionMapper.class,
                // 505
                HttpVersionNotSupportedExceptionMapper.class,
                // 511
                NetworkAuthenticationRequiredExceptionMapper.class);
    }
}
