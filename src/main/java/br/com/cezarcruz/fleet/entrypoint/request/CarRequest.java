package br.com.cezarcruz.fleet.entrypoint.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CarRequest {
  private String model;
  private Integer mileage;
  private String plate;
}
