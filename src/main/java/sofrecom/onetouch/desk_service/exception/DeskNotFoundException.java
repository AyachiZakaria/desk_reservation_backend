package sofrecom.onetouch.desk_service.exception;

public class DeskNotFoundException extends RuntimeException {
    public DeskNotFoundException(String message) {
        super(message);
    }
}