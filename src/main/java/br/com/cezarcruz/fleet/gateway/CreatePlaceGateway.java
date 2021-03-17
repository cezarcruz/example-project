package br.com.cezarcruz.fleet.gateway;

import br.com.cezarcruz.fleet.core.model.PlaceModel;

public interface CreatePlaceGateway {
  PlaceModel create(final PlaceModel placeModel);
}
