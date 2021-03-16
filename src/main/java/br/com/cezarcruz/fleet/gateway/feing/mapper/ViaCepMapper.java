package br.com.cezarcruz.fleet.gateway.feing.mapper;

import br.com.cezarcruz.fleet.gateway.json.ViaCepResponse;
import br.com.cezarcruz.fleet.model.AddressModel;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ViaCepMapper {

  @Mapping(target = "city", source = "localidade")
  @Mapping(target = "street", source = "logradouro")
  AddressModel fromJson(final ViaCepResponse viaCepResponse);

}
