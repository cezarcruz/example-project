package br.com.cezarcruz.fleet.gateway;

import br.com.cezarcruz.fleet.model.CarModel;

public interface SaveCarGateway {
  CarModel save(final CarModel carModel);
}
