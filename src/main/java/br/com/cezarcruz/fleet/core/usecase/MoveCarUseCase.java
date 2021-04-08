package br.com.cezarcruz.fleet.core.usecase;

import br.com.cezarcruz.fleet.core.model.CarModel;
import br.com.cezarcruz.fleet.core.model.CarStatus;
import br.com.cezarcruz.fleet.gateway.MoveCarGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MoveCarUseCase {

  private final MoveCarGateway moveCarGateway;

  public CarModel execute(final CarModel carModel) {

    final CarModel activeCar = carModel.toBuilder()
        .status(CarStatus.ACTIVE)
        .build();

    return moveCarGateway.move(activeCar);

  }

}
