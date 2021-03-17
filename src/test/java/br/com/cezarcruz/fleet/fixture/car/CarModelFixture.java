package br.com.cezarcruz.fleet.fixture.car;

import br.com.cezarcruz.fleet.core.model.CarModel;


public class CarModelFixture {

  public static CarModel validCarModel() {
    return CarModel.builder()
        .plate("cvy1234")
        .model("FORD FIESTA")
        .mileage(100_000)
        .build();
  }

}
