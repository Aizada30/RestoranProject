package global.exceptionGlobal;

public class AlreadyException extends RuntimeException{
    public AlreadyException(String message) {
        super(message);
    }
}