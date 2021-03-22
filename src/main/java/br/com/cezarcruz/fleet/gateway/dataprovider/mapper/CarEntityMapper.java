package br.com.cezarcruz.fleet.gateway.dataprovider.mapper;

import br.com.cezarcruz.fleet.core.model.CarStatus;
import br.com.cezarcruz.fleet.gateway.dataprovider.entity.CarEntity;
import br.com.cezarcruz.fleet.core.model.CarModel;
import java.util.Objects;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarEntityMapper {
  CarEntity from(final CarModel carModel);
  CarModel from(final CarEntity carEntity);

  default String fromStatusEnum(final CarStatus status) {
    if (Objects.isNull(status)) {
      return null;
    }

    return status.getLabel();

  }

  default CarStatus fromString(final String status) {
    if (Objects.isNull(status)) {
      return null;
    }

    return CarStatus.of(status);

  }

}
