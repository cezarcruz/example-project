package br.com.cezarcruz.fleet.gateway.dataprovider;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import br.com.cezarcruz.fleet.fixture.CarModelFixture;
import br.com.cezarcruz.fleet.gateway.SaveCarGateway;
import br.com.cezarcruz.fleet.model.CarModel;
import br.com.cezarcruz.fleet.utils.DataBaseIntegrationAbstract;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.hamcrest.beans.SamePropertyValuesAs;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class SaveCarDataProviderIntegrationTest extends DataBaseIntegrationAbstract {

  @Autowired
  private SaveCarGateway saveCarGateway;

  @BeforeAll
  public static void setUp() {
    FixtureFactoryLoader.loadTemplates("br.com.cezarcruz.fleet.fixture");
  }

  @Test
  @DisplayName("deve validar se o carro foi salvo no banco corretamente")
  void shouldCreateCarWithSuccess() {

    final CarModel carModel =
        Fixture.from(CarModel.class)
            .gimme(CarModelFixture.VALID_CAR_MODEL);

    final CarModel created = saveCarGateway.save(carModel);

    assertThat(created, SamePropertyValuesAs.samePropertyValuesAs(carModel, "id"));
    assertThat(created.getId(), notNullValue());
  }
}
