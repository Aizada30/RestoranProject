package global.exceptionGlobal.handlerException;

import lombok.Builder;
import org.springframework.http.HttpStatus;

/**
 * Abdyrazakova Aizada
 */
@Builder
public class ExceptionResponse {
    private HttpStatus httpStatus;
    private String message;
    private String exceptionClass;
}
