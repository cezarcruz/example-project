package br.com.cezarcruz.fleet.fixture;

import br.com.cezarcruz.fleet.entrypoint.request.CarRequest;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class CarRequestFixture implements TemplateLoader {

  public static final String VALID_CAR_REQUEST = "valid_car_request";

  @Override
  public void load() {
    Fixture.of(CarRequest.class).addTemplate(VALID_CAR_REQUEST, new Rule() {
      {
        add("plate", "cvy1234");
        add("model", "FORD FIESTA");
        add("mileage", 100_000);
      }
    });
  }
}
