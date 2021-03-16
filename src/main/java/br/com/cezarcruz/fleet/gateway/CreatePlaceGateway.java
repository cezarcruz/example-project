package br.com.cezarcruz.fleet.gateway;

import br.com.cezarcruz.fleet.model.PlaceModel;

public interface CreatePlaceGateway {
  PlaceModel create(final PlaceModel placeModel);
}
