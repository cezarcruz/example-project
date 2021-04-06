package br.com.cezarcruz.fleet.entrypoint;


import br.com.cezarcruz.fleet.core.model.CarModel;
import br.com.cezarcruz.fleet.core.usecase.MoveCarUseCase;
import br.com.cezarcruz.fleet.entrypoint.exception.ValidateException;
import br.com.cezarcruz.fleet.entrypoint.mapper.CarMapper;
import br.com.cezarcruz.fleet.entrypoint.request.MoveCarRequest;
import br.com.cezarcruz.fleet.entrypoint.response.CarResponse;
import br.com.cezarcruz.fleet.entrypoint.validator.MoveCarValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/car")
@AllArgsConstructor
public class MoveCarController {

  private final MoveCarUseCase moveCarUseCase;
  private final CarMapper carMapper;
  private final MoveCarValidator carValidator;

  @PostMapping("/move")
  public ResponseEntity<CarResponse> moveTo(@RequestBody final MoveCarRequest moveCarRequest) {

    carValidator.validate(moveCarRequest)
        .isInvalidThrow(ValidateException.class);

    final CarModel movedCar =
        moveCarUseCase.execute(moveCarRequest.getCarPlate(), moveCarRequest.getPlaceId());

    final CarResponse carResponse = carMapper.from(movedCar);

    return ResponseEntity.ok(carResponse);
  }

}
