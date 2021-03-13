package br.com.cezarcruz.fleet.entrypoint.validator;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

import br.com.cezarcruz.fleet.entrypoint.request.CarRequest;
import br.com.fluentvalidator.AbstractValidator;
import org.springframework.stereotype.Component;

@Component
public class CarValidator extends AbstractValidator<CarRequest> {

  @Override
  public void rules() {

    ruleFor(CarRequest::getMileage)
        .must(not(nullValue()))
          .withMessage("mileage must be not null")
          .withFieldName("mileage")
        .critical();

    ruleFor(CarRequest::getPlate)
        .must(not(nullValue()))
          .withMessage("plate must be not null")
          .withFieldName("plate")
        .critical();

    ruleFor(CarRequest::getModel)
        .must(not(nullValue()))
          .withMessage("model must be not null")
          .withFieldName("model")
        .critical();
  }
}
