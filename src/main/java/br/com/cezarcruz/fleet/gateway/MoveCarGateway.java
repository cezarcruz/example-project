package br.com.cezarcruz.fleet.gateway;

import br.com.cezarcruz.fleet.core.model.CarModel;

public interface MoveCarGateway {
  CarModel move(final CarModel carModel);
}
