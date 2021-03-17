package br.com.cezarcruz.fleet.fixture.place;

import br.com.cezarcruz.fleet.core.model.PlaceModel;

public class PlaceModelFixture {

  public static PlaceModel getValidaWithAddress() {
    return PlaceModel.builder()
        .address(AddressModelFixture.getValidAddress())
        .description("1h from here")
        .build();
  }

}
