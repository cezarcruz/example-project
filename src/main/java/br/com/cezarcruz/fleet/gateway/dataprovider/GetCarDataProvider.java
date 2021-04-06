package br.com.cezarcruz.fleet.gateway.dataprovider;

import br.com.cezarcruz.fleet.core.model.CarModel;
import br.com.cezarcruz.fleet.gateway.GetCarGateway;
import br.com.cezarcruz.fleet.gateway.dataprovider.mapper.CarEntityMapper;
import br.com.cezarcruz.fleet.gateway.dataprovider.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetCarDataProvider implements GetCarGateway {

  private final CarRepository carRepository;
  private final CarEntityMapper carMapper;

  @Override
  public CarModel get(final String plate) {
    return carRepository.findByPlate(plate)
        .map(carMapper::from)
        .orElseThrow(() -> new RuntimeException("car not found exception"));
  }
}
