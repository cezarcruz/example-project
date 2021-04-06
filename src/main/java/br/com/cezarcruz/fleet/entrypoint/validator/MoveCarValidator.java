package br.com.cezarcruz.fleet.entrypoint.validator;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

import br.com.cezarcruz.fleet.entrypoint.request.MoveCarRequest;
import br.com.fluentvalidator.AbstractValidator;
import org.springframework.stereotype.Component;

@Component
public class MoveCarValidator extends AbstractValidator<MoveCarRequest> {

  @Override
  public void rules() {

    ruleFor(MoveCarRequest::getCarPlate)
        .must(not(nullValue()))
          .withMessage("car plate must be not null")
          .withFieldName("car_plate")
        .critical();

    ruleFor(MoveCarRequest::getPlaceId)
        .must(not(nullValue()))
        .withMessage("place id must be not null")
        .withFieldName("place_id")
        .critical();

  }
}
