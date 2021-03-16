package br.com.cezarcruz.fleet.entrypoint.mapper;

import br.com.cezarcruz.fleet.entrypoint.request.AddressRequest;
import br.com.cezarcruz.fleet.entrypoint.response.AddressResponse;
import br.com.cezarcruz.fleet.model.AddressModel;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AddressMapper {
  AddressModel fromRequest(final AddressRequest request);
  AddressResponse fromModel(final AddressModel addressModel);
}
