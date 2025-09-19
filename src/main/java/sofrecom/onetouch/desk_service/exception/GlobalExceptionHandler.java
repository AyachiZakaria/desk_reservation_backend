package sofrecom.onetouch.desk_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sofrecom.onetouch.desk_service.exception.DeskNotFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DeskNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleDeskNotFoundException(DeskNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}