package br.com.cezarcruz.fleet.fixture.place;

import br.com.cezarcruz.fleet.model.AddressModel;

public class AddressModelFixture {

  public static AddressModel getValidAddress() {
    return AddressModel.builder()
        .cep("12312312")
        .number("123A")
        .street("1th Avenue")
        .city("Ohio")
        .build();
  }
}
