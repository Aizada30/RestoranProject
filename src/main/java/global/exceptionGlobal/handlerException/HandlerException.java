package global.exceptionGlobal.handlerException;

import global.exceptionGlobal.AlreadyException;
import global.exceptionGlobal.BadCredentialException;
import global.exceptionGlobal.BadRequest;
import global.exceptionGlobal.NotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * Abdyrazakova Aizada
 */
@RestControllerAdvice
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


 @ExceptionHandler(BadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse badRequest(BadRequest e) {
        return ExceptionResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .exceptionClass(e.getClass().getSimpleName())
                .message(e.getMessage())
                .build();

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleMethodArgumentNotValid(MethodArgumentNotValidException e){
        List<String> errors = e
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        return ExceptionResponse
                .builder()
                .message(errors.toString())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .exceptionClass(e.getClass().getSimpleName())
                .build();
    }
}
