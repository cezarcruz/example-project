package br.com.cezarcruz.fleet.gateway.dataprovider.mapper;

import br.com.cezarcruz.fleet.gateway.dataprovider.entity.PlaceEntity;
import br.com.cezarcruz.fleet.model.PlaceModel;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", imports = AddressEntityMapper.class,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PlaceEntityMapper {

  PlaceEntity fromModel(final PlaceModel placeModel);

  PlaceModel fromEntity(final PlaceEntity placeEntity);
}
