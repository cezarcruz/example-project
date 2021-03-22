package br.com.cezarcruz.fleet.entrypoint.mapper;

import br.com.cezarcruz.fleet.core.model.CarModel;
import br.com.cezarcruz.fleet.core.model.CarStatus;
import br.com.cezarcruz.fleet.entrypoint.request.CarRequest;
import br.com.cezarcruz.fleet.entrypoint.response.CarResponse;
import java.util.Objects;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {

  CarModel from(final CarRequest carRequest);

  CarResponse from(final CarModel carModel);

  default String statusToString(final CarStatus status) {

    if (Objects.isNull(status)) {
      return null;
    }

    return status.getLabel();

  }

}
