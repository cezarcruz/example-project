package br.com.cezarcruz.fleet.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Builder(toBuilder = true)
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CarModel {
  private Long id;
  private String plate;
  private Integer mileage;
  private String model;
  private PlaceModel actualPlace;
  private CarStatus status;
}
