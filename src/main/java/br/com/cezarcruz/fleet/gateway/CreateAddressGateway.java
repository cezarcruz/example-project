package br.com.cezarcruz.fleet.gateway;

import br.com.cezarcruz.fleet.core.model.AddressModel;

public interface CreateAddressGateway {
  AddressModel create(final AddressModel addressModel);
}
