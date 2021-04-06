package br.com.cezarcruz.fleet.entrypoint.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CarResponse {
  private Long id;
  private String plate;
  private Integer mileage;
  private String model;
  private String status;

  @JsonProperty("actual_place")
  private PlaceResponse actualPlace;
}
