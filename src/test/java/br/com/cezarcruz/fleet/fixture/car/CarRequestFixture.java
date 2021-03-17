package br.com.cezarcruz.fleet.fixture.car;

import br.com.cezarcruz.fleet.entrypoint.request.CarRequest;

public class CarRequestFixture {

  public static CarRequest getValidCarRequest() {
    return CarRequest.builder()
        .plate("cvy1234")
        .model("FORD FIESTA")
        .mileage(100)
        .build();
  }

  public static CarRequest getCarWithoutMileage() {
    return getValidCarRequest()
        .toBuilder()
        .mileage(null)
        .build();
  }

  public static CarRequest getEmpty() {
    return CarRequest.builder().build();
  }

}
