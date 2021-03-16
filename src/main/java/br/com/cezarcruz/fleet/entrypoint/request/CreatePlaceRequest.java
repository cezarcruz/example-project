package br.com.cezarcruz.fleet.entrypoint.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatePlaceRequest {
  private String description;
  private AddressRequest address;
}
