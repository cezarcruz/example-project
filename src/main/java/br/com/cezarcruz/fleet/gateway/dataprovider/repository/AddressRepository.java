package br.com.cezarcruz.fleet.gateway.dataprovider.repository;

import br.com.cezarcruz.fleet.gateway.dataprovider.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

}
