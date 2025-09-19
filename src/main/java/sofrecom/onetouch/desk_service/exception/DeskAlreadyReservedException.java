package sofrecom.onetouch.desk_service.exception;

public class DeskAlreadyReservedException extends RuntimeException {
    public DeskAlreadyReservedException(String message) {
        super(message);
    }
}