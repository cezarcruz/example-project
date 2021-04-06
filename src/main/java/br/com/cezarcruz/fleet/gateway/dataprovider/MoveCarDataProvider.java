package br.com.cezarcruz.fleet.gateway.dataprovider;

import br.com.cezarcruz.fleet.core.model.CarModel;
import br.com.cezarcruz.fleet.gateway.MoveCarGateway;
import br.com.cezarcruz.fleet.gateway.dataprovider.entity.CarEntity;
import br.com.cezarcruz.fleet.gateway.dataprovider.entity.PlaceEntity;
import br.com.cezarcruz.fleet.gateway.dataprovider.mapper.CarEntityMapper;
import br.com.cezarcruz.fleet.gateway.dataprovider.repository.CarRepository;
import br.com.cezarcruz.fleet.gateway.dataprovider.repository.PlaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MoveCarDataProvider implements MoveCarGateway {

  private final CarRepository carRepository;
  private final PlaceRepository placeRepository;
  private final CarEntityMapper carEntityMapper;

  @Override
  public CarModel move(final CarModel carModel) {

    //TODO refactor
    final PlaceEntity place = getPlace(carModel.getActualPlace().getId());
    final CarEntity car = getCar(carModel.getPlate());

    final CarEntity carWithPlace = car.toBuilder().actualPlace(place).build();
    final CarEntity savedCar = saveCar(carWithPlace);

    return carEntityMapper.from(savedCar);

  }

  private PlaceEntity getPlace(final Long placeId) {
    return placeRepository.findById(placeId)
        .orElseThrow(() -> new RuntimeException("Place not found"));
  }

  private CarEntity getCar(final String plate) {
    return carRepository.findByPlate(plate)
        .orElseThrow(() -> new RuntimeException("Car not found"));
  }

  private CarEntity saveCar(final CarEntity carEntity) {
    return carRepository.save(carEntity);
  }

}
