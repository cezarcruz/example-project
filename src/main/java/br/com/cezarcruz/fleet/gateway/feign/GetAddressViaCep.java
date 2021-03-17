package br.com.cezarcruz.fleet.gateway.feign;

import br.com.cezarcruz.fleet.gateway.GetAddressGateway;
import br.com.cezarcruz.fleet.gateway.feign.mapper.ViaCepMapper;
import br.com.cezarcruz.fleet.gateway.feign.viacep.ViaCepFeignClient;
import br.com.cezarcruz.fleet.gateway.json.ViaCepResponse;
import br.com.cezarcruz.fleet.model.AddressModel;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAddressViaCep implements GetAddressGateway {

  private final ViaCepFeignClient viaCepFeignClient;
  private final ViaCepMapper viaCepMapper;

  @Override
  public Optional<AddressModel> get(final String cep) {
    final ViaCepResponse response = viaCepFeignClient.getBy(cep);
    final AddressModel addressModel = viaCepMapper.fromJson(response);
    return Optional.of(addressModel);
  }
}
