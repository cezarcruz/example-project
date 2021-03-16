package br.com.cezarcruz.fleet.entrypoint.response;

import lombok.Data;

@Data
public class CarResponse {
  private Long id;
  private String plate;
  private Integer mileage;
  private String model;
}
