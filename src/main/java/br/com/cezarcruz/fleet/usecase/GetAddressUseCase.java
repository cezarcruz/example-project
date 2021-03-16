package br.com.cezarcruz.fleet.usecase;

import br.com.cezarcruz.fleet.gateway.GetAddressGateway;
import br.com.cezarcruz.fleet.model.AddressModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAddressUseCase {

  private final GetAddressGateway getAddressGateway;

  public AddressModel getAddress(final String cep) {
    return getAddressGateway.get(cep)
        .orElseThrow(() -> new RuntimeException("erro"));
  }

}
