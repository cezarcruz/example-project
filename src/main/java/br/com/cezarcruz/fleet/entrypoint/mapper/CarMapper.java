package br.com.cezarcruz.fleet.entrypoint.mapper;

import br.com.cezarcruz.fleet.core.model.CarModel;
import br.com.cezarcruz.fleet.core.model.CarStatus;
import br.com.cezarcruz.fleet.entrypoint.request.CarRequest;
import br.com.cezarcruz.fleet.entrypoint.request.MoveCarRequest;
import br.com.cezarcruz.fleet.entrypoint.response.CarResponse;
import java.util.Objects;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarMapper {

  CarModel from(final CarRequest carRequest);

  CarResponse from(final CarModel carModel);

  @Mapping(source = "moveCarRequest.carPlate", target = "plate")
  @Mapping(source = "moveCarRequest.placeId", target = "actualPlace.id")
  CarModel from(final MoveCarRequest moveCarRequest);

  default String statusToString(final CarStatus status) {

    if (Objects.isNull(status)) {
      return null;
    }

    return status.getLabel();

  }

}
