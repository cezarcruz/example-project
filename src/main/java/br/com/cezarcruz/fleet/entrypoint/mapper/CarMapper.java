package br.com.cezarcruz.fleet.entrypoint.mapper;

import br.com.cezarcruz.fleet.entrypoint.request.CarRequest;
import br.com.cezarcruz.fleet.entrypoint.response.CarResponse;
import br.com.cezarcruz.fleet.model.CarModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {
  CarModel from(final CarRequest carRequest);
  CarResponse from(final CarModel carModel);
}
