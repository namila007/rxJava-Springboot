package me.namila.rxspringboot.exceptionHandler;

import com.mongodb.MongoException;
import me.namila.rxspringboot.exceptionHandler.model.APIError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(MongoException.class)
  @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
  public ResponseEntity<APIError> handleMongoExceptions(MongoException ex) {
    return new ResponseEntity<>(new APIError(HttpStatus.NOT_ACCEPTABLE, "MongoDB ERROR OCCURRED", ex),
        HttpStatus.NOT_ACCEPTABLE);
  }

  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<APIError> resourceEntityNotFound(RuntimeException ex) {
    APIError apiError = new APIError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), ex);
    return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}