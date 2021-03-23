package br.com.cezarcruz.fleet.gateway.viacep;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import br.com.cezarcruz.fleet.core.model.AddressModel;
import br.com.cezarcruz.fleet.gateway.GetAddressGateway;
import br.com.cezarcruz.fleet.utils.WiremockIntegrationAbstract;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

class GetAddressGatewayIntegrationTest extends WiremockIntegrationAbstract {

  @Autowired
  private WireMockServer wireMockServer;

  @Autowired
  private GetAddressGateway getAddressGateway;

  @AfterEach
  void afterEach() {
    wireMockServer.resetAll();
  }

  @Test
  @DisplayName("deve buscar pelo cep informado")
  void shouldGetAddressByGiverCep() {

    wireMockServer.stubFor(
        WireMock.get("/ws/13188021/json/")
            .withHeader("Content-Type", WireMock.equalTo(MediaType.APPLICATION_JSON_VALUE))
            .willReturn(
                WireMock.aResponse()
                    .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .withBodyFile("wiremock/viacep/cep-01001000-success.json")
            )
    );

    final Optional<AddressModel> responseOptional = getAddressGateway.get("13188021");

    assertThat(responseOptional, notNullValue());
    assertThat(responseOptional.isPresent(), is(true));

    assertThat(responseOptional.get().getCep(), is("01001-000"));
    assertThat(responseOptional.get().getCity(), is("São Paulo"));

  }

}
