package br.com.cezarcruz.fleet.gateway.dataprovider.repository;

import br.com.cezarcruz.fleet.gateway.dataprovider.entity.CarEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<CarEntity, Long> {
  Optional<CarEntity> findByPlate(final String plate);
}
