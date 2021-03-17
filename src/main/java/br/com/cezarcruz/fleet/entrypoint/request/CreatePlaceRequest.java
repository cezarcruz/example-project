package br.com.cezarcruz.fleet.entrypoint.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlaceRequest {
  private String description;
  private AddressRequest address;
}
