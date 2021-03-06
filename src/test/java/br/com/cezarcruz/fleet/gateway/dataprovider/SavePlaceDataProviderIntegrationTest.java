package br.com.cezarcruz.fleet.gateway.dataprovider;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import br.com.cezarcruz.fleet.core.model.PlaceModel;
import br.com.cezarcruz.fleet.fixture.place.PlaceModelFixture;
import br.com.cezarcruz.fleet.gateway.CreatePlaceGateway;
import br.com.cezarcruz.fleet.utils.DataBaseIntegrationAbstract;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class SavePlaceDataProviderIntegrationTest extends DataBaseIntegrationAbstract {

  @Autowired
  private CreatePlaceGateway createPlaceGateway;

  @Test
  void shouldSavePlace() {
    final PlaceModel placeModel =
        createPlaceGateway.create(PlaceModelFixture.getValidWithAddress());

    assertThat(placeModel, notNullValue());
    assertThat(placeModel.getId(), notNullValue());
  }

}
