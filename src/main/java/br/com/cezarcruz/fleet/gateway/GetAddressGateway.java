package br.com.cezarcruz.fleet.gateway;

import br.com.cezarcruz.fleet.core.model.AddressModel;
import java.util.Optional;

public interface GetAddressGateway {
  Optional<AddressModel> get(final String addressModel);
}
