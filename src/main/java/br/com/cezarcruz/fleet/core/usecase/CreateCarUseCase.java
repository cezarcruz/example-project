package br.com.cezarcruz.fleet.core.usecase;

import br.com.cezarcruz.fleet.gateway.CreateCarGateway;
import br.com.cezarcruz.fleet.core.model.CarModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateCarUseCase {

  private final CreateCarGateway createCarGateway;

  public CarModel create(final CarModel carModel) {
    return createCarGateway.save(carModel);
  }
}
