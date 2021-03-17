package br.com.cezarcruz.fleet.gateway.dataprovider;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import br.com.cezarcruz.fleet.fixture.car.CarModelFixture;
import br.com.cezarcruz.fleet.gateway.CreateCarGateway;
import br.com.cezarcruz.fleet.gateway.dataprovider.mapper.CarEntityMapperImpl;
import br.com.cezarcruz.fleet.core.model.CarModel;
import br.com.cezarcruz.fleet.utils.DataBaseIntegrationAbstract;
import org.hamcrest.beans.SamePropertyValuesAs;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

@Import({CreateCarDataProvider.class, CarEntityMapperImpl.class})
class SaveCarDataProviderIntegrationTest extends DataBaseIntegrationAbstract {

  @Autowired
  private CreateCarGateway createCarGateway;

  @Test
  @DisplayName("deve validar se o carro foi salvo no banco corretamente")
  void shouldCreateCarWithSuccess() {

    final CarModel carModel = CarModelFixture.validCarModel();
    final CarModel created = createCarGateway.save(carModel);

    assertThat(created, SamePropertyValuesAs.samePropertyValuesAs(carModel, "id"));
    assertThat(created.getId(), notNullValue());
  }
}
