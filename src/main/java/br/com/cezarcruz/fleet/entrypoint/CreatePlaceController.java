package br.com.cezarcruz.fleet.entrypoint;

import br.com.cezarcruz.fleet.entrypoint.exception.ValidateException;
import br.com.cezarcruz.fleet.entrypoint.mapper.PlaceMapper;
import br.com.cezarcruz.fleet.entrypoint.request.CreatePlaceRequest;
import br.com.cezarcruz.fleet.entrypoint.response.PlaceResponse;
import br.com.cezarcruz.fleet.entrypoint.validator.PlaceValidator;
import br.com.cezarcruz.fleet.model.PlaceModel;
import br.com.cezarcruz.fleet.usecase.CreatePlaceUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/place")
@AllArgsConstructor
public class CreatePlaceController {

  private final PlaceMapper placeMapper;
  private final PlaceValidator placeValidator;
  private final CreatePlaceUseCase createPlaceUseCase;

  @PostMapping
  public ResponseEntity<PlaceResponse> createPlace(
      @RequestBody final CreatePlaceRequest createPlaceRequest) {

    placeValidator.validate(createPlaceRequest).isInvalidThrow(ValidateException.class);

    final PlaceModel placeModel = placeMapper.fromRequest(createPlaceRequest);
    final PlaceModel createdPlace = createPlaceUseCase.execute(placeModel);
    final PlaceResponse placeResponse = placeMapper.fromModel(createdPlace);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(placeResponse);

  }

}
