package br.com.cezarcruz.fleet.entrypoint.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressResponse {
  private Long id;
  private String cep;
  private String number;
  private String street;
  private String city;
}
