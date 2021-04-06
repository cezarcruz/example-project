package br.com.cezarcruz.fleet.core.usecase;

import br.com.cezarcruz.fleet.core.model.CarModel;
import br.com.cezarcruz.fleet.gateway.MoveCarGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MoveCarUseCase {

  private final MoveCarGateway moveCarGateway;

  public CarModel execute(final String plate, final Long placeId) {
    return moveCarGateway.move(plate, placeId);
  }

}
