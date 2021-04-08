package br.com.cezarcruz.fleet.entrypoint.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class MoveCarRequest {

  @JsonProperty("car_plate")
  private String carPlate;

  @JsonProperty("place_id")
  private Long placeId;
}
