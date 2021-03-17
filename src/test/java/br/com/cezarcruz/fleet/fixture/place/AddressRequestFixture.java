package br.com.cezarcruz.fleet.fixture.place;

import br.com.cezarcruz.fleet.entrypoint.request.AddressRequest;

public class AddressRequestFixture {

  public static AddressRequest getValidAddress() {
    return AddressRequest.builder()
        .cep("13188023")
        .complement("Somewhere in time")
        .number("123A")
        .build();
  }
}
