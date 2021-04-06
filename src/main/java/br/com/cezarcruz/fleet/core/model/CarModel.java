package br.com.cezarcruz.fleet.core.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
public class CarModel {
  private final Long id;
  private final String plate;
  private final Integer mileage;
  private final String model;
  private final PlaceModel actualPlace;
  private final CarStatus status;
}
