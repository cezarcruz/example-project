package br.com.cezarcruz.fleet.usecase;

import br.com.cezarcruz.fleet.gateway.CreateAddressGateway;
import br.com.cezarcruz.fleet.gateway.CreatePlaceGateway;
import br.com.cezarcruz.fleet.model.AddressModel;
import br.com.cezarcruz.fleet.model.PlaceModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreatePlaceUseCase {

  private final GetAddressUseCase getAddressUseCase;
  private final CreateAddressGateway createAddressGateway;
  private final CreatePlaceGateway createPlaceGateway;

  public PlaceModel execute(final PlaceModel placeModel) {

    final AddressModel address = getAddressUseCase.
        getAddress(placeModel.getAddress().getCep());

    final AddressModel addressModel = createAddressGateway.create(address);

    final AddressModel addressWithNumber =
        addressModel.toBuilder().number(placeModel.getAddress().getNumber()).build();

    return createPlaceGateway.create(placeModel.toBuilder().address(addressWithNumber).build());

  }
}
