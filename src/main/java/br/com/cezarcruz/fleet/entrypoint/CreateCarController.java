package br.com.cezarcruz.fleet.entrypoint;

import br.com.cezarcruz.fleet.entrypoint.exception.ValidateException;
import br.com.cezarcruz.fleet.entrypoint.mapper.CarMapper;
import br.com.cezarcruz.fleet.entrypoint.request.CarRequest;
import br.com.cezarcruz.fleet.entrypoint.response.CarResponse;
import br.com.cezarcruz.fleet.entrypoint.validator.CarValidator;
import br.com.cezarcruz.fleet.model.CarModel;
import br.com.cezarcruz.fleet.usecase.CreateCarUseCase;
import br.com.fluentvalidator.context.ValidationResult;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/car")
@AllArgsConstructor
public class CreateCarController {

  private final CarValidator carValidator;
  private final CarMapper carMapper;
  private final CreateCarUseCase createCarUseCase;

  @PostMapping
  public ResponseEntity<CarResponse> create(@RequestBody final CarRequest carRequest) {
    final ValidationResult validationResult = carValidator.validate(carRequest);
    validationResult.isInvalidThrow(ValidateException.class);

    final CarModel carModel = carMapper.from(carRequest);
    final CarModel createdCar = createCarUseCase.create(carModel);
    final CarResponse carResponse = carMapper.from(createdCar);

    return ResponseEntity.status(HttpStatus.CREATED).body(carResponse);
  }

}
