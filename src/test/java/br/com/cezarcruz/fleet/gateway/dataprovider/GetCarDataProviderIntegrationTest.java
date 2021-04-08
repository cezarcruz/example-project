package br.com.cezarcruz.fleet.gateway.dataprovider;

import static org.hamcrest.MatcherAssert.assertThat;

import br.com.cezarcruz.fleet.core.model.CarModel;
import br.com.cezarcruz.fleet.fixture.car.CarModelFixture;
import br.com.cezarcruz.fleet.gateway.CreateCarGateway;
import br.com.cezarcruz.fleet.gateway.GetCarGateway;
import br.com.cezarcruz.fleet.utils.DataBaseIntegrationAbstract;
import org.hamcrest.beans.SamePropertyValuesAs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class GetCarDataProviderIntegrationTest extends DataBaseIntegrationAbstract {

  @Autowired
  private GetCarGateway getCarGateway;

  @Autowired
  private CreateCarGateway createCarGateway;

  @Test
  void shouldRetrieveACarByPlate() {
    final CarModel carModel = CarModelFixture.validCarModel();
    final CarModel created = createCarGateway.save(carModel);
    final CarModel found = getCarGateway.get(carModel.getPlate());

    assertThat(created, SamePropertyValuesAs.samePropertyValuesAs(found));
  }

}
