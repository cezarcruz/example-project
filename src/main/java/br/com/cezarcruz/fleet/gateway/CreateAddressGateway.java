package br.com.cezarcruz.fleet.gateway;

import br.com.cezarcruz.fleet.model.AddressModel;

public interface CreateAddressGateway {
  AddressModel create(final AddressModel addressModel);
}
