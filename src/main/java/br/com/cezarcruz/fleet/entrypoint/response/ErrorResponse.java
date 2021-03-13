package br.com.cezarcruz.fleet.entrypoint.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class ErrorResponse {
  private String code;
  private String message;
  private List<ErrorFieldResponse> fields;
}
