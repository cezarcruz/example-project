package br.com.cezarcruz.fleet.gateway;

import br.com.cezarcruz.fleet.core.model.CarModel;

public interface CreateCarGateway {
  CarModel save(final CarModel carModel);
}
