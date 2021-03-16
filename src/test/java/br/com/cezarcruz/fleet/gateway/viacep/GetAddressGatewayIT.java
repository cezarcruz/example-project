package br.com.cezarcruz.fleet.gateway.viacep;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import br.com.cezarcruz.fleet.gateway.feing.viacep.ViaCepFeingClient;
import br.com.cezarcruz.fleet.gateway.json.ViaCepResponse;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ActiveProfiles("wiremock")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = WiremockConfiguration.class)
class GetAddressGatewayIT {

  @Autowired
  private WireMockServer wireMockServer;

  @Autowired
  private ViaCepFeingClient viaCepFeingClient;

  @AfterEach
  void afterEach() {
    wireMockServer.resetAll();
  }

  @Test
  void shouldDoSomething() {

    wireMockServer.stubFor(
        WireMock.get("/ws/13188021/json/")
            .withHeader("Content-Type", WireMock.equalTo(MediaType.APPLICATION_JSON_VALUE))
            .willReturn(
                WireMock.aResponse()
                    .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .withBody("{"
                        + "  \"cep\": \"01001-000\","
                        + "  \"logradouro\": \"Praça da Sé\","
                        + "  \"complemento\": \"lado ímpar\","
                        + "  \"bairro\": \"Sé\","
                        + "  \"localidade\": \"São Paulo\","
                        + "  \"uf\": \"SP\","
                        + "  \"ibge\": \"3550308\","
                        + "  \"gia\": \"1004\","
                        + "  \"ddd\": \"11\","
                        + "  \"siafi\": \"7107\""
                        + "}")
            )
    );

    final ViaCepResponse viaCepResponse = viaCepFeingClient.getBy("13188021");

    assertThat(viaCepResponse, notNullValue());
    assertThat(viaCepResponse.getCep(), is("01001-000"));
    assertThat(viaCepResponse.getLocalidade(), is("São Paulo"));


  }

}
