package br.com.cezarcruz.fleet.entrypoint.mapper;

import br.com.cezarcruz.fleet.entrypoint.request.CreatePlaceRequest;
import br.com.cezarcruz.fleet.entrypoint.response.PlaceResponse;
import br.com.cezarcruz.fleet.core.model.PlaceModel;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = AddressMapper.class,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PlaceMapper {

  PlaceResponse fromModel(final PlaceModel placeModel);

  PlaceModel fromRequest(final CreatePlaceRequest request);
}
