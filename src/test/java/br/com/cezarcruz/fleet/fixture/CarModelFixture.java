package br.com.cezarcruz.fleet.fixture;

import br.com.cezarcruz.fleet.model.CarModel;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class CarModelFixture implements TemplateLoader {

  public static final String VALID_CAR_MODEL = "valida_car_model";

  @Override
  public void load() {
    Fixture.of(CarModel.class).addTemplate(VALID_CAR_MODEL, new Rule() {
      {
        add("plate", "cvy1234");
        add("model", "FORD FIESTA");
        add("mileage", 100_000);
      }
    });
  }
}
