package br.com.cezarcruz.fleet.usecase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import br.com.cezarcruz.fleet.fixture.place.AddressModelFixture;
import br.com.cezarcruz.fleet.fixture.place.PlaceModelFixture;
import br.com.cezarcruz.fleet.gateway.CreateAddressGateway;
import br.com.cezarcruz.fleet.gateway.CreatePlaceGateway;
import br.com.cezarcruz.fleet.model.AddressModel;
import br.com.cezarcruz.fleet.model.PlaceModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreatePlaceUseCaseTest {

  @InjectMocks
  private CreatePlaceUseCase createPlaceUseCase;

  @Mock
  private GetAddressUseCase getAddressUseCase;

  @Mock
  private CreatePlaceGateway createPlaceGateway;

  @Mock
  private CreateAddressGateway createAddressGateway;

  @Test
  void shouldCreatePlaceSuccessfully() {

    when(getAddressUseCase.getAddress(anyString()))
        .thenReturn(AddressModelFixture.getValidAddress());

    when(createAddressGateway.create(any(AddressModel.class)))
        .thenAnswer(a -> a.getArgument(0));

    when(createPlaceGateway.create(any()))
        .thenAnswer(a -> a.getArgument(0));

    final PlaceModel placeModel = createPlaceUseCase
        .execute(PlaceModelFixture.getValidaWithAddress());

    assertThat(placeModel, notNullValue());

  }

}
