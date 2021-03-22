package br.com.cezarcruz.fleet.core.usecase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import br.com.cezarcruz.fleet.core.model.AddressModel;
import br.com.cezarcruz.fleet.gateway.GetAddressGateway;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetAddressUseCaseUnitTest {

  @InjectMocks
  private GetAddressUseCase getAddressUseCase;

  @Mock
  private GetAddressGateway getAddressGateway;

  @Test
  void shouldNotFindAnAddress() {

    when(getAddressGateway.get("12312312"))
        .thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> {
      getAddressUseCase.getAddress("12312312");
    });

  }

  @Test
  void shouldFindAnAddress() {
    when(getAddressGateway.get("12312312"))
        .thenReturn(Optional.of(AddressModel.builder().cep("12312312").build()));

    final AddressModel address = getAddressUseCase.getAddress("12312312");

    assertThat(address, notNullValue());
  }

}
