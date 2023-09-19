package global.exceptionGlobal.handlerException;

import global.exceptionGlobal.AlreadyException;
import global.exceptionGlobal.BadCredentialException;
import global.exceptionGlobal.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Abdyrazakova Aizada
 */
public class HandlerException {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse notFoundException(NotFoundException e) {
        return ExceptionResponse.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .exceptionClass(e.getClass().getSimpleName())
                .message(e.getMessage())
                .build();

    }

    @ExceptionHandler(AlreadyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse alreadyException(AlreadyException e) {
        return ExceptionResponse.builder()
                .httpStatus(HttpStatus.CONFLICT)
                .exceptionClass(e.getClass().getSimpleName())
                .message(e.getMessage())
                .build();

    }

    @ExceptionHandler(BadCredentialException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse badCredentialException(BadCredentialException e) {
        return ExceptionResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .exceptionClass(e.getClass().getSimpleName())
                .message(e.getMessage())
                .build();

    }

}
