package br.com.cezarcruz.fleet.entrypoint.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MoveCarRequest {

  @JsonProperty("car_plate")
  private String carPlate;

  @JsonProperty("place_id")
  private Long placeId;
}
