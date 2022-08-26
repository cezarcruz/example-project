package br.com.cezarcruz.fleet.core.usecase;

import br.com.cezarcruz.fleet.core.model.AddressModel;
import br.com.cezarcruz.fleet.gateway.GetAddressGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAddressUseCase {

  private final GetAddressGateway getAddressGateway;

  public AddressModel getAddress(@NonNull final String cep) {
    return getAddressGateway.get(cep)
        .orElseThrow(() -> new RuntimeException("erro"));
  }

}
