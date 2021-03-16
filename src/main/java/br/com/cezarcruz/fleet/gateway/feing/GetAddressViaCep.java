package br.com.cezarcruz.fleet.gateway.feing;

import br.com.cezarcruz.fleet.gateway.GetAddressGateway;
import br.com.cezarcruz.fleet.gateway.feing.mapper.ViaCepMapper;
import br.com.cezarcruz.fleet.gateway.feing.viacep.ViaCepFeingClient;
import br.com.cezarcruz.fleet.gateway.json.ViaCepResponse;
import br.com.cezarcruz.fleet.model.AddressModel;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAddressViaCep implements GetAddressGateway {

  private final ViaCepFeingClient viaCepFeingClient;
  private final ViaCepMapper viaCepMapper;

  @Override
  public Optional<AddressModel> get(final String cep) {
    final ViaCepResponse response = viaCepFeingClient.getBy(cep);
    final AddressModel addressModel = viaCepMapper.fromJson(response);
    return Optional.of(addressModel);
  }
}
