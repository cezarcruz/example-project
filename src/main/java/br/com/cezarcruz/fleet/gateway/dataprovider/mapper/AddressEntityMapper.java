package br.com.cezarcruz.fleet.gateway.dataprovider.mapper;

import br.com.cezarcruz.fleet.gateway.dataprovider.entity.AddressEntity;
import br.com.cezarcruz.fleet.core.model.AddressModel;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AddressEntityMapper {

  AddressEntity fromModel(final AddressModel addressModel);
  AddressModel fromEntity(final AddressEntity addressEntity);

}
