package br.com.cezarcruz.fleet.core.usecase;

import br.com.cezarcruz.fleet.core.model.CarModel;
import br.com.cezarcruz.fleet.core.model.CarStatus;
import br.com.cezarcruz.fleet.gateway.CreateCarGateway;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateCarUseCase {

  private final CreateCarGateway createCarGateway;

  public CarModel create(@NonNull final CarModel carModel) {

    final CarModel carWithStatus = carModel.toBuilder()
        .status(CarStatus.CREATED)
        .build();

    return createCarGateway.save(carWithStatus);
  }
}
