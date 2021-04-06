package br.com.cezarcruz.fleet.entrypoint;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.cezarcruz.fleet.core.model.CarModel;
import br.com.cezarcruz.fleet.core.model.CarStatus;
import br.com.cezarcruz.fleet.core.usecase.CreateCarUseCase;
import br.com.cezarcruz.fleet.entrypoint.exception.ValidateException;
import br.com.cezarcruz.fleet.entrypoint.mapper.CarMapper;
import br.com.cezarcruz.fleet.entrypoint.mapper.CarMapperImpl;
import br.com.cezarcruz.fleet.entrypoint.request.CarRequest;
import br.com.cezarcruz.fleet.entrypoint.response.CarResponse;
import br.com.cezarcruz.fleet.entrypoint.validator.CarValidator;
import br.com.cezarcruz.fleet.fixture.car.CarRequestFixture;
import br.com.fluentvalidator.context.ValidationResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class CreateCarControllerUnitTest {

  @Mock
  private CreateCarUseCase createCarUseCase;

  @Spy
  private final CarValidator carValidator = new CarValidator();

  @Spy
  private final CarMapper carMapper = new CarMapperImpl();

  @InjectMocks
  private CreateCarController createCarController;

  @Test
  @DisplayName("deve validar todos os inputs enviados")
  void shouldTestInvalidInput() {

    final CarRequest carRequest = CarRequestFixture.getEmpty();

    final ValidateException validateException = assertThrows(ValidateException.class, () -> {
      createCarController.create(carRequest);
    });

    assertThat(validateException, notNullValue());

    final ValidationResult validationResult = validateException.getValidationResult();

    assertThat(validationResult, notNullValue());
    assertThat(validationResult.getErrors(), hasSize(3));

    verify(carMapper, times(0)).from(any(CarModel.class));
    verify(carMapper, times(0)).from(any(CarRequest.class));
    verify(carValidator, times(1)).validate(any(CarRequest.class));

  }

  @Test
  @DisplayName("retornar apenas uma validacao do campo mileage")
  void shouldValidateOneRule() {
    final CarRequest carRequest = CarRequestFixture.getCarWithoutMileage();

    final ValidateException validateException = assertThrows(ValidateException.class, () -> {
      createCarController.create(carRequest);
    });

    assertThat(validateException, notNullValue());

    final ValidationResult validationResult = validateException.getValidationResult();

    assertThat(validationResult, notNullValue());
    assertThat(validationResult.getErrors(), hasSize(1));

    verify(carMapper, times(0)).from(any(CarModel.class));
    verify(carMapper, times(0)).from(any(CarRequest.class));
    verify(carValidator, times(1)).validate(any(CarRequest.class));

  }

  @Test
  @DisplayName("deve aceitar todos os campos validos")
  void shouldAcceptValidRequest() {

    final CarRequest carRequest = CarRequestFixture.getValidCarRequest();

    when(createCarUseCase.create(any(CarModel.class)))
        .thenAnswer(a -> {
          final CarModel argument = a.getArgument(0);
          argument.setStatus(CarStatus.CREATED);
          return argument;
        });

    final ResponseEntity<CarResponse> carResponseResponseEntity
        = createCarController.create(carRequest);

    assertThat(carResponseResponseEntity.getStatusCode(), is(HttpStatus.CREATED));

    final CarResponse carResponse = carResponseResponseEntity.getBody();

    assertThat(carResponse, notNullValue());
    assertThat(carResponse.getPlate(), is(carRequest.getPlate()));
    assertThat(carResponse.getModel(), is(carRequest.getModel()));
    assertThat(carResponse.getMileage(), is(carRequest.getMileage()));
    assertThat(carResponse.getStatus(), is("created"));

    verify(createCarUseCase, times(1)).create(any());
    verify(carMapper, times(1)).from(any(CarModel.class));
    verify(carMapper, times(1)).from(any(CarRequest.class));
    verify(carValidator, times(1)).validate(any(CarRequest.class));

  }

}
