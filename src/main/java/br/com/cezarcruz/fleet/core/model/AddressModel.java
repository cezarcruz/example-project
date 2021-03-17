package br.com.cezarcruz.fleet.core.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class AddressModel {
  private final Long id;
  private final String cep;
  private final String number;
  private final String street;
  private final String city;
}
