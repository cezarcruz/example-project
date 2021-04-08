package br.com.cezarcruz.fleet.entrypoint;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import br.com.cezarcruz.fleet.core.usecase.MoveCarUseCase;
import br.com.cezarcruz.fleet.entrypoint.mapper.CarMapper;
import br.com.cezarcruz.fleet.entrypoint.mapper.CarMapperImpl;
import br.com.cezarcruz.fleet.entrypoint.request.MoveCarRequest;
import br.com.cezarcruz.fleet.entrypoint.response.CarResponse;
import br.com.cezarcruz.fleet.entrypoint.validator.MoveCarValidator;
import br.com.cezarcruz.fleet.fixture.car.MoveCarRequestFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class MoveCarControllerUnitTest {

  @Mock
  private MoveCarUseCase moveCarUseCase;

  @Spy
  private final CarMapper carMapper = new CarMapperImpl();

  @Spy
  private final MoveCarValidator carValidator = new MoveCarValidator();

  @InjectMocks
  private MoveCarController moveCarController;

  @Test
  void shouldMoveAValidCar() {

    final MoveCarRequest moveCarRequest = MoveCarRequestFixture.validRequest();
    final ResponseEntity<CarResponse> carResponseResponseEntity = moveCarController.moveTo(moveCarRequest);
    assertThat(carResponseResponseEntity.getStatusCode(), is(HttpStatus.OK));
  }

}
