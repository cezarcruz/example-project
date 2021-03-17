package br.com.cezarcruz.fleet.entrypoint;


import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.cezarcruz.fleet.entrypoint.mapper.CarMapperImpl;
import br.com.cezarcruz.fleet.entrypoint.request.CarRequest;
import br.com.cezarcruz.fleet.entrypoint.validator.CarValidator;
import br.com.cezarcruz.fleet.model.CarModel;
import br.com.cezarcruz.fleet.usecase.CreateCarUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CreateCarController.class)
class CreateCarControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private CreateCarUseCase createCarUseCase;

  @SpyBean
  private CarValidator carValidator;

  @SpyBean
  private CarMapperImpl carMapper;

  @Test
  @DisplayName("deve validar todos os inputs enviados para o endpoint /v1/car")
  void shouldTestInvalidInput() throws Exception {

    final CarRequest carRequest = CarRequest.builder().build();

    this.mockMvc.perform(
        post("/v1/car")
            .content(objectMapper.writeValueAsString(carRequest))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
    ).andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.code").value("400"))
        .andExpect(jsonPath("$.message").value("validation error"))
        .andExpect(jsonPath("$.fields").isArray())
        .andExpect(jsonPath("$.fields", hasSize(3)))
        .andExpect(jsonPath("$.fields[*].message").value(
            containsInAnyOrder("mileage must be not null",
                "model must be not null",
                "plate must be not null"))
        )
        .andExpect(jsonPath("$.fields[*].field").value(
            containsInAnyOrder("mileage", "model", "plate")
        ))
    ;
  }

  @Test
  @DisplayName("retornar apenas uma validacao do campo mileage")
  void shouldValidateOneRule() throws Exception {
    final CarRequest carRequest = CarRequest.builder()
        .model("FORD FIESTA")
        .plate("CVY1234")
        .build();

    this.mockMvc.perform(
        post("/v1/car")
            .content(objectMapper.writeValueAsString(carRequest))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
    ).andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.code").value("400"))
        .andExpect(jsonPath("$.message").value("validation error"))
        .andExpect(jsonPath("$.fields").isArray())
        .andExpect(jsonPath("$.fields", hasSize(1)))
        .andExpect(jsonPath("$.fields[*].message").value(
            containsInAnyOrder("mileage must be not null")
        ))
        .andExpect(jsonPath("$.fields[*].field").value(
            containsInAnyOrder("mileage")
        ))
    ;
  }

  @Test
  @DisplayName("deve aceitar todos os campos validos")
  void shouldAcceptValidRequest() throws Exception {
    final CarRequest carRequest = CarRequest.builder()
        .mileage(100)
        .model("FORD FIESTA")
        .plate("CVY1234")
        .build();

    when(createCarUseCase.create(any()))
        .thenAnswer( a -> a.getArgument(0));

    this.mockMvc.perform(post("/v1/car")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(objectMapper.writeValueAsString(carRequest))
    ).andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.plate").value(carRequest.getPlate()))
        .andExpect(jsonPath("$.mileage").value(carRequest.getMileage()))
        .andExpect(jsonPath("$.model").value(carRequest.getModel()))
    ;

    verify(createCarUseCase, times(1)).create(any());
    verify(carMapper, times(1)).from(any(CarModel.class));
    verify(carMapper, times(1)).from(any(CarRequest.class));
    verify(carValidator, times(1)).validate(any(CarRequest.class));

  }

}
