package br.com.cezarcruz.fleet.gateway.dataprovider;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import br.com.cezarcruz.fleet.fixture.car.CarModelFixture;
import br.com.cezarcruz.fleet.gateway.SaveCarGateway;
import br.com.cezarcruz.fleet.gateway.dataprovider.mapper.CarEntityMapperImpl;
import br.com.cezarcruz.fleet.model.CarModel;
import br.com.cezarcruz.fleet.utils.DataBaseIntegrationAbstract;
import org.hamcrest.beans.SamePropertyValuesAs;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

@Import({SaveCarDataProvider.class, CarEntityMapperImpl.class})
class SaveCarDataProviderIntegrationTest extends DataBaseIntegrationAbstract {

  @Autowired
  private SaveCarGateway saveCarGateway;

  @Test
  @DisplayName("deve validar se o carro foi salvo no banco corretamente")
  void shouldCreateCarWithSuccess() {

    final CarModel carModel = CarModelFixture.validCarModel();
    final CarModel created = saveCarGateway.save(carModel);

    assertThat(created, SamePropertyValuesAs.samePropertyValuesAs(carModel, "id"));
    assertThat(created.getId(), notNullValue());
  }
}
