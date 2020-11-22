package me.namila.rxspringboot.exceptionHandler.model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class APIError {

  private HttpStatus status;

  @JsonFormat(shape = JsonFormat.Shape.STRING,
      pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime timestamp;

  private String message;
  private String debugMessage;

  public APIError() {
    timestamp = LocalDateTime.now();
  }

  public APIError(HttpStatus status) {
    this();
    this.status = status;
  }

  public APIError(HttpStatus status, Throwable ex) {
    this();
    this.status = status;
    this.message = "Unexpected error";
    this.debugMessage = ex.getLocalizedMessage();
  }

  public APIError(HttpStatus status, String message, Throwable ex) {
    this();
    this.status = status;
    this.message = message;
    this.debugMessage = ex.getMessage();
  }

}