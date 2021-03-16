package br.com.cezarcruz.fleet.gateway.dataprovider;

import br.com.cezarcruz.fleet.gateway.CreateAddressGateway;
import br.com.cezarcruz.fleet.gateway.CreatePlaceGateway;
import br.com.cezarcruz.fleet.gateway.dataprovider.entity.AddressEntity;
import br.com.cezarcruz.fleet.gateway.dataprovider.entity.PlaceEntity;
import br.com.cezarcruz.fleet.gateway.dataprovider.mapper.AddressEntityMapper;
import br.com.cezarcruz.fleet.gateway.dataprovider.mapper.PlaceEntityMapper;
import br.com.cezarcruz.fleet.gateway.dataprovider.repository.AddressRepository;
import br.com.cezarcruz.fleet.gateway.dataprovider.repository.PlaceRepository;
import br.com.cezarcruz.fleet.model.AddressModel;
import br.com.cezarcruz.fleet.model.PlaceModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SavePlaceDataProvider implements CreateAddressGateway, CreatePlaceGateway {

  private final AddressRepository addressRepository;
  private final PlaceRepository placeRepository;
  private final PlaceEntityMapper placeMapper;
  private final AddressEntityMapper addressEntityMapper;

  @Override
  public AddressModel create(final AddressModel addressModel) {

    final AddressEntity addressEntity = addressEntityMapper.fromModel(addressModel);
    final AddressEntity addressSaved = addressRepository.save(addressEntity);

    return addressModel.toBuilder().id(addressSaved.getId()).build();

  }

  @Override
  public PlaceModel create(final PlaceModel placeModel) {

    final AddressModel savedAddress = create(placeModel.getAddress());

    final PlaceEntity placeEntity = placeMapper
        .fromModel(placeModel.toBuilder().address(savedAddress)
            .build());

    final PlaceEntity savedPlace = placeRepository.save(placeEntity);

    return placeMapper.fromEntity(savedPlace);
  }
}
