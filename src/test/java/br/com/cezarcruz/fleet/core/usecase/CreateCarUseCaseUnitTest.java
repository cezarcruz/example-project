package br.com.cezarcruz.fleet.core.usecase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import br.com.cezarcruz.fleet.fixture.car.CarModelFixture;
import br.com.cezarcruz.fleet.gateway.CreateCarGateway;
import br.com.cezarcruz.fleet.core.model.CarModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateCarUseCaseUnitTest {

  @InjectMocks
  private CreateCarUseCase createCarUseCase;

  @Mock
  private CreateCarGateway createCarGateway;


  @Test
  @DisplayName("deve validar se o use case createCarUseCase contem as regras de negocio validas")
  void shouldCreateCar() {

    final CarModel carModel = CarModelFixture.validCarModel();

    when(createCarGateway.save(carModel)).thenReturn(carModel);

    final CarModel createdCar = createCarUseCase.create(carModel);
    assertThat(createdCar, notNullValue());

    verify(createCarGateway, times(1)).save(carModel);
    verifyNoMoreInteractions(createCarGateway);

  }

}
