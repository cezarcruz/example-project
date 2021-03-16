package br.com.cezarcruz.fleet.entrypoint.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlaceResponse {

  private Long id;
  private String description;
  private AddressResponse address;

}
