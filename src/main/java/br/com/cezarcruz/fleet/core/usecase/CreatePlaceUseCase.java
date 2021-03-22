package br.com.cezarcruz.fleet.core.usecase;

import br.com.cezarcruz.fleet.gateway.CreateAddressGateway;
import br.com.cezarcruz.fleet.gateway.CreatePlaceGateway;
import br.com.cezarcruz.fleet.core.model.AddressModel;
import br.com.cezarcruz.fleet.core.model.PlaceModel;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreatePlaceUseCase {

  private final GetAddressUseCase getAddressUseCase;
  private final CreateAddressGateway createAddressGateway;
  private final CreatePlaceGateway createPlaceGateway;

  public PlaceModel execute(@NonNull final PlaceModel placeModel) {

    final AddressModel address = getAddressUseCase.
        getAddress(placeModel.getAddress().getCep());

    final AddressModel addressModel = createAddressGateway.create(address);

    final AddressModel addressWithNumber =
        addressModel.toBuilder().number(placeModel.getAddress().getNumber()).build();

    return createPlaceGateway.create(placeModel.toBuilder().address(addressWithNumber).build());

  }
}
