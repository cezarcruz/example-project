package br.com.cezarcruz.fleet.entrypoint.mapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import br.com.cezarcruz.fleet.entrypoint.request.CarRequest;
import br.com.cezarcruz.fleet.fixture.car.CarRequestFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarMapperUnitTest {

  //TODO this test is so weak
  @Test
  @DisplayName("deve validar a conversao de CarRequest para CarModel")
  void shouldConvertRequestToModel() {

    final CarRequest carRequest = CarRequestFixture.getValidCarRequest();

    assertThat(carRequest, notNullValue());
    assertThat(carRequest.getMileage(), equalTo(100));
  }

}
