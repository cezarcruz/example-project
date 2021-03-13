package br.com.cezarcruz.fleet.gateway.dataprovider;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import br.com.cezarcruz.fleet.FleetApplication;
import br.com.cezarcruz.fleet.fixture.CarModelFixture;
import br.com.cezarcruz.fleet.gateway.SaveCarGateway;
import br.com.cezarcruz.fleet.model.CarModel;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.hamcrest.beans.SamePropertyValuesAs;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DirtiesContext
@ContextConfiguration(classes = DataProviderConfiguration.class)
@SpringBootTest(classes = FleetApplication.class)
@ActiveProfiles("dataprovider")
public class SaveCarDataProviderIT {

  @Autowired
  private SaveCarGateway saveCarGateway;

  @BeforeAll
  public static void setUp() {
    FixtureFactoryLoader.loadTemplates("br.com.cezarcruz.fleet.fixture");
  }

  @Test
  public void shouldCreateCarWithSuccess() {

    final CarModel carModel = Fixture.from(CarModel.class).gimme(CarModelFixture.VALID_CAR_MODEL);

    final CarModel created = saveCarGateway.save(carModel);

    assertThat(created, SamePropertyValuesAs.samePropertyValuesAs(carModel, "id"));
    assertThat(created.getId(), notNullValue());
  }
}
