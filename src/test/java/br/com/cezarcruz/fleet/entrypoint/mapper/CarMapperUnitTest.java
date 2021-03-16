package br.com.cezarcruz.fleet.entrypoint.mapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import br.com.cezarcruz.fleet.entrypoint.request.CarRequest;
import br.com.cezarcruz.fleet.fixture.CarRequestFixture;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarMapperUnitTest {

  @BeforeAll
  public static void setUp() {
    FixtureFactoryLoader.loadTemplates("br.com.cezarcruz.fleet.fixture");
  }

  //TODO this test is so weak
  @Test
  @DisplayName("deve validar a conversao de CarRequest para CarModel")
  void shouldConvertRequestToModel() {

    final CarRequest carRequest =
        Fixture.from(CarRequest.class)
            .gimme(CarRequestFixture.VALID_CAR_REQUEST);

    assertThat(carRequest, notNullValue());
    assertThat(carRequest.getMileage(), equalTo(100_000));
  }

}
