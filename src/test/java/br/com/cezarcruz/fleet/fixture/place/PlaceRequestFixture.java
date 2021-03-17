package br.com.cezarcruz.fleet.fixture.place;

import br.com.cezarcruz.fleet.entrypoint.request.AddressRequest;
import br.com.cezarcruz.fleet.entrypoint.request.CreatePlaceRequest;

public class PlaceRequestFixture {

  public static CreatePlaceRequest getPlaceWithAddress(final AddressRequest addressRequest) {
    return CreatePlaceRequest.builder()
        .description("just another place")
        .address(addressRequest)
        .build();
  }

  public static CreatePlaceRequest getEmpty() {
    return CreatePlaceRequest.builder().build();
  }
}
