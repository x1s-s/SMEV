package by.x1ss.smev.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
        return ResponseEntity.badRequest().body(e.getMessage().substring(e.getMessage().indexOf(':') + 1));
    }

    @ExceptionHandler(NotFoundPenaltyException.class)
    public ResponseEntity<Object> handleNotFoundPenaltyException(){
        return ResponseEntity.noContent().build();
    }
}
