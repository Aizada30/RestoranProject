package global.exceptionGlobal.handlerException;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Abdyrazakova Aizada
 */
@Builder
@Getter
public class ExceptionResponse {
    private HttpStatus httpStatus;
    private String message;
    private String exceptionClass;
}
