package br.com.cezarcruz.fleet.component;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.cezarcruz.fleet.entrypoint.request.CarRequest;
import br.com.cezarcruz.fleet.fixture.car.CarRequestFixture;
import br.com.cezarcruz.fleet.utils.ComponentAbstract;
import br.com.cezarcruz.fleet.utils.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

class CarComponentTest extends ComponentAbstract {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("deve criar um carro")
  void shouldCreateCarSuccessfully() throws Exception {
    final CarRequest carRequest = CarRequestFixture.getValidCarRequest();

    this.mockMvc.perform(post("/v1/car")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(JsonUtils.jsonFrom(carRequest))
        .characterEncoding("utf-8")
    ).andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.plate").value(carRequest.getPlate()))
        .andExpect(jsonPath("$.mileage").value(carRequest.getMileage()))
        .andExpect(jsonPath("$.model").value(carRequest.getModel()))
        .andExpect(jsonPath("$.status").value("created"))
    ;
  }

}
