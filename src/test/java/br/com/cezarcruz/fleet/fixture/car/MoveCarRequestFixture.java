package br.com.cezarcruz.fleet.fixture.car;

import br.com.cezarcruz.fleet.entrypoint.request.MoveCarRequest;

public class MoveCarRequestFixture {

  public static final MoveCarRequest validRequest() {
    return MoveCarRequest.builder()
        .carPlate("cvv2345")
        .placeId(1L)
        .build();

  }

}
