package br.com.cezarcruz.fleet.component;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.cezarcruz.fleet.entrypoint.request.AddressRequest;
import br.com.cezarcruz.fleet.entrypoint.request.CreatePlaceRequest;
import br.com.cezarcruz.fleet.fixture.place.AddressRequestFixture;
import br.com.cezarcruz.fleet.fixture.place.PlaceRequestFixture;
import br.com.cezarcruz.fleet.utils.ComponentAbstract;
import br.com.cezarcruz.fleet.utils.JsonUtils;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

class PlaceComponentTest extends ComponentAbstract {

  @Autowired
  private WireMockServer wireMockServer;

  @Autowired
  private MockMvc mockMvc;

  @AfterEach
  void afterEach() {
    wireMockServer.resetAll();
  }

  @Test
  void shouldCreatePlace() throws Exception {

    final AddressRequest address = AddressRequestFixture.getValidAddress();
    final CreatePlaceRequest placeRequest = PlaceRequestFixture.getPlaceWithAddress(address);

    wireMockServer.stubFor(
        WireMock.get("/ws/" + address.getCep() + "/json/")
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

    this.mockMvc.perform(post("/v1/place")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(JsonUtils.jsonFrom(placeRequest))
    ).andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.description").value("just another place"))
        .andExpect(jsonPath("$.id").isNotEmpty())
    ;

  }

}
