package br.com.cezarcruz.fleet.core.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class PlaceModel {
  private final Long id;
  private final String description;
  private final AddressModel address;
}
