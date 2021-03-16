package br.com.cezarcruz.fleet.entrypoint.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressRequest {
  private String cep;
  private String number;
  private String complement;
}
