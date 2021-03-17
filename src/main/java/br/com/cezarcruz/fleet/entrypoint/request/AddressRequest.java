package br.com.cezarcruz.fleet.entrypoint.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {
  private String cep;
  private String number;
  private String complement;
}
