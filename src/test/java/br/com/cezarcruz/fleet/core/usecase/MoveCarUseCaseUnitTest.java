package br.com.cezarcruz.fleet.core.usecase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.cezarcruz.fleet.core.model.CarModel;
import br.com.cezarcruz.fleet.core.model.CarStatus;
import br.com.cezarcruz.fleet.fixture.car.CarModelFixture;
import br.com.cezarcruz.fleet.gateway.GetCarGateway;
import br.com.cezarcruz.fleet.gateway.MoveCarGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MoveCarUseCaseUnitTest {

  @Mock
  private MoveCarGateway moveCarGateway;

  @Mock
  private GetCarGateway getCarGateway;

  @InjectMocks
  private MoveCarUseCase moveCarUseCase;

  @Test
  void shouldMoveCarToFirstPlace() {

    final CarModel carModel = CarModelFixture.validCarModel();

    when(getCarGateway.get(carModel.getPlate()))
        .thenReturn(carModel);

    when(moveCarGateway.move(any()))
        .thenAnswer(a -> a.getArgument(0));

    final CarModel movedCar = moveCarUseCase.execute(carModel);

    assertThat(movedCar, notNullValue());
    assertThat(movedCar.getStatus(), is(CarStatus.ACTIVE));

  }
}