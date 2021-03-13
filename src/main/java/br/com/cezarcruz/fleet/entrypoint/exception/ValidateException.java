package br.com.cezarcruz.fleet.entrypoint.exception;

import br.com.fluentvalidator.context.ValidationResult;
import br.com.fluentvalidator.exception.ValidationException;

public class ValidateException extends ValidationException {
  public ValidateException(final ValidationResult validationResult) {
    super(validationResult);
  }
}
