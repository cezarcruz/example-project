package br.com.cezarcruz.fleet.gateway.dataprovider.repository;

import br.com.cezarcruz.fleet.gateway.dataprovider.entity.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<PlaceEntity, Long> {

}
