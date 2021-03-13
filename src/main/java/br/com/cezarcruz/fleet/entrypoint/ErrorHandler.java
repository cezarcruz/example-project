package br.com.cezarcruz.fleet.entrypoint;

import br.com.cezarcruz.fleet.entrypoint.exception.ValidateException;
import br.com.cezarcruz.fleet.entrypoint.response.ErrorFieldResponse;
import br.com.cezarcruz.fleet.entrypoint.response.ErrorResponse;
import br.com.fluentvalidator.exception.ValidationException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

  @ExceptionHandler(ValidateException.class)
  public ResponseEntity<ErrorResponse> handleValidationException(
      final ValidationException validationException) {

    final List<ErrorFieldResponse> errorResponses = validationException.getValidationResult()
        .getErrors()
        .stream()
        .map(error -> ErrorFieldResponse.builder().field(error.getField())
            .message(error.getMessage()).build())
        .collect(Collectors.toList());

    final ErrorResponse errorResponse =
        ErrorResponse.builder().message("validation error")
            .code("400").fields(errorResponses).build();

    return ResponseEntity.badRequest().body(errorResponse);

  }
}
