package br.com.cezarcruz.fleet.gateway.dataprovider.repository;

import br.com.cezarcruz.fleet.gateway.dataprovider.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<CarEntity, Long> {
}
