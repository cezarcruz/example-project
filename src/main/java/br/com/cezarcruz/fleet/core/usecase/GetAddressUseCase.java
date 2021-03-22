package br.com.cezarcruz.fleet.core.usecase;

import br.com.cezarcruz.fleet.gateway.GetAddressGateway;
import br.com.cezarcruz.fleet.core.model.AddressModel;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAddressUseCase {

  private final GetAddressGateway getAddressGateway;

  public AddressModel getAddress(@NonNull final String cep) {
    return getAddressGateway.get(cep)
        .orElseThrow(() -> new RuntimeException("erro"));
  }

}
