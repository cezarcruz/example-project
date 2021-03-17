package br.com.cezarcruz.fleet.gateway.dataprovider.mapper;

import br.com.cezarcruz.fleet.gateway.dataprovider.entity.CarEntity;
import br.com.cezarcruz.fleet.core.model.CarModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarEntityMapper {
  CarEntity from(final CarModel carModel);
  CarModel from(final CarEntity carEntity);
}
