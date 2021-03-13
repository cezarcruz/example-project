package br.com.cezarcruz.fleet.entrypoint.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarRequest {
  private String model;
  private Integer mileage;
  private String plate;
}
