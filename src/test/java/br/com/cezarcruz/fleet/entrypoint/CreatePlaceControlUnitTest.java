package br.com.cezarcruz.fleet.entrypoint;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.cezarcruz.fleet.core.model.PlaceModel;
import br.com.cezarcruz.fleet.core.usecase.CreatePlaceUseCase;
import br.com.cezarcruz.fleet.entrypoint.exception.ValidateException;
import br.com.cezarcruz.fleet.entrypoint.mapper.AddressMapperImpl;
import br.com.cezarcruz.fleet.entrypoint.mapper.PlaceMapper;
import br.com.cezarcruz.fleet.entrypoint.mapper.PlaceMapperImpl;
import br.com.cezarcruz.fleet.entrypoint.request.AddressRequest;
import br.com.cezarcruz.fleet.entrypoint.request.CreatePlaceRequest;
import br.com.cezarcruz.fleet.entrypoint.response.PlaceResponse;
import br.com.cezarcruz.fleet.entrypoint.validator.AddressValidator;
import br.com.cezarcruz.fleet.entrypoint.validator.PlaceValidator;
import br.com.cezarcruz.fleet.fixture.place.AddressRequestFixture;
import br.com.cezarcruz.fleet.fixture.place.PlaceRequestFixture;
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
class CreatePlaceControlUnitTest {

  @InjectMocks
  private CreatePlaceController controller;

  @Spy
  private final PlaceMapper placeMapper = new PlaceMapperImpl(new AddressMapperImpl());

  @Spy
  private final PlaceValidator placeValidator = new PlaceValidator(new AddressValidator());

  @Mock
  private CreatePlaceUseCase createPlaceUseCase;

  @Test
  @DisplayName("deve aceitar a requisicao para criar um novo local")
  void shouldAcceptValidInput() throws Exception {

    final AddressRequest address = AddressRequestFixture.getValidAddress();
    final CreatePlaceRequest placeRequest = PlaceRequestFixture.getPlaceWithAddress(address);

    when(createPlaceUseCase.execute(any()))
        .thenAnswer(a -> ((PlaceModel) a.getArgument(0)).toBuilder().id(1L).build());

    final ResponseEntity<PlaceResponse> responseResponseEntity =
        controller.createPlace(placeRequest);

    assertThat(responseResponseEntity.getStatusCode(), is(HttpStatus.CREATED));

    final PlaceResponse placeResponse = responseResponseEntity.getBody();
    assertThat(placeResponse, notNullValue());
    assertThat(placeResponse.getDescription(), is(placeRequest.getDescription()));

  }

  @Test
  void shouldRejectInvalidFields() throws Exception {

    final CreatePlaceRequest placeRequest = PlaceRequestFixture.getEmpty();

    final ValidateException validateException = assertThrows(ValidateException.class, () -> {
      controller.createPlace(placeRequest);
    });

    assertThat(validateException.getValidationResult().getErrors(), hasSize(2));

  }

}
