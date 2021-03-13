package br.com.cezarcruz.fleet.usecase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import br.com.cezarcruz.fleet.fixture.CarModelFixture;
import br.com.cezarcruz.fleet.gateway.SaveCarGateway;
import br.com.cezarcruz.fleet.model.CarModel;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreateCarUseCaseUnitTest {

  @InjectMocks
  private CreateCarUseCase createCarUseCase;

  @Mock
  private SaveCarGateway saveCarGateway;

  @BeforeAll
  public static void setUp() {
    FixtureFactoryLoader.loadTemplates("br.com.cezarcruz.fleet.fixture");
  }

  @Test
  public void shouldCreateCar() {
    final CarModel carModel = Fixture.from(CarModel.class).gimme(CarModelFixture.VALID_CAR_MODEL);

    when(saveCarGateway.save(carModel)).thenReturn(carModel);

    final CarModel createdCar = createCarUseCase.create(carModel);
    assertThat(createdCar, notNullValue());

    verify(saveCarGateway, times(1)).save(carModel);
    verifyNoMoreInteractions(saveCarGateway);

  }

}
