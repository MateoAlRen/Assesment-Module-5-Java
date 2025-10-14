package errors;
public class ServiceException extends RuntimeException {
    public ServiceException(String msg, Throwable cause) { super(msg, cause); }
    public ServiceException(String msg) { super(msg); }
}
