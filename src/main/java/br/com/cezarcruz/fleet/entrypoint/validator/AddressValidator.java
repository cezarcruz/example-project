package br.com.cezarcruz.fleet.entrypoint.validator;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

import br.com.cezarcruz.fleet.entrypoint.request.AddressRequest;
import br.com.fluentvalidator.AbstractValidator;
import org.springframework.stereotype.Component;

@Component
public class AddressValidator extends AbstractValidator<AddressRequest> {

  @Override
  public void rules() {

    failFastRule();

    ruleFor(AddressRequest::getNumber)
        .must(not(nullValue()))
          .withMessage("number must be not null")
          .withFieldName("number")
        .critical();

    ruleFor(AddressRequest::getCep)
        .must(not(nullValue()))
          .withMessage("cep must be not null")
          .withFieldName("cep")
        .critical();

    ruleFor(AddressRequest::getComplement)
        .must(not(nullValue()))
          .withMessage("complement must be not null")
          .withFieldName("complement")
        .critical();

  }
}
