package br.com.cezarcruz.fleet.gateway;

import br.com.cezarcruz.fleet.core.model.CarModel;

public interface GetCarGateway {
  CarModel get(final String plate);
}
