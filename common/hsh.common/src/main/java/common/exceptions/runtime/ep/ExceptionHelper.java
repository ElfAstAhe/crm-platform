package common.exceptions.runtime.ep;

import javax.ws.rs.core.Response;

class ExceptionHelper {
    static Response validate(Response response, Response.Status expectedStatus) {
        if (expectedStatus.getStatusCode() != response.getStatus()) {
            throw new IllegalArgumentException(String.format("Invalid response status code. Expected [%d], was [%d].",
                    expectedStatus.getStatusCode(), response.getStatus()));
        }
        return response;
    }
}
