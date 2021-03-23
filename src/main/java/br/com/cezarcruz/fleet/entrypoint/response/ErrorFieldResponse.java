package br.com.cezarcruz.fleet.entrypoint.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorFieldResponse {
  private String field;
  private String value;
  private String message;
}
