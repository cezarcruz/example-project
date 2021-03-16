package br.com.cezarcruz.fleet.entrypoint.validator;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

import br.com.cezarcruz.fleet.entrypoint.request.CreatePlaceRequest;
import br.com.fluentvalidator.AbstractValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PlaceValidator extends AbstractValidator<CreatePlaceRequest> {

  private final AddressValidator addressValidator;

  @Override
  public void rules() {

    failFastRule();

    ruleFor(CreatePlaceRequest::getDescription)
        .must(not(nullValue()))
          .withMessage("description must be not null")
          .withFieldName("description")
        .critical();

    ruleFor(CreatePlaceRequest::getAddress)
        .must(not(nullValue()))
          .withMessage("address must be not null")
          .withFieldName("address")
        .whenever(not(nullValue()))
          .withValidator(addressValidator)
        .critical();

  }
}
