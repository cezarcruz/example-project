package br.com.cezarcruz.fleet.usecase;

import br.com.cezarcruz.fleet.gateway.SaveCarGateway;
import br.com.cezarcruz.fleet.model.CarModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateCarUseCase {

  private final SaveCarGateway saveCarGateway;

  public CarModel create(final CarModel carModel) {
    return saveCarGateway.save(carModel);
  }
}
