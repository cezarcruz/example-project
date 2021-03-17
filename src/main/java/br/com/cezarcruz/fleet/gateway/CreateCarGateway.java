package br.com.cezarcruz.fleet.gateway;

import br.com.cezarcruz.fleet.model.CarModel;

public interface CreateCarGateway {
  CarModel save(final CarModel carModel);
}
