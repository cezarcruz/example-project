package br.com.cezarcruz.fleet.gateway.dataprovider;

import br.com.cezarcruz.fleet.gateway.CreateCarGateway;
import br.com.cezarcruz.fleet.gateway.dataprovider.entity.CarEntity;
import br.com.cezarcruz.fleet.gateway.dataprovider.mapper.CarEntityMapper;
import br.com.cezarcruz.fleet.gateway.dataprovider.repository.CarRepository;
import br.com.cezarcruz.fleet.core.model.CarModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CreateCarDataProvider implements CreateCarGateway {

  private final CarRepository carRepository;
  private final CarEntityMapper carEntityMapper;

  @Override
  public CarModel save(final CarModel carModel) {
    final CarEntity carEntity = carEntityMapper.from(carModel);
    final CarEntity savedCar = carRepository.save(carEntity);

    log.info("saved car: {}", savedCar);

    return carEntityMapper.from(savedCar);
  }
}
