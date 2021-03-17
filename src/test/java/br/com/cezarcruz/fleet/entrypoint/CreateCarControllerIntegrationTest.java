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

import br.com.cezarcruz.fleet.entrypoint.mapper.CarMapper;
import br.com.cezarcruz.fleet.entrypoint.mapper.CarMapperImpl;
import br.com.cezarcruz.fleet.entrypoint.request.CarRequest;
import br.com.cezarcruz.fleet.entrypoint.validator.CarValidator;
import br.com.cezarcruz.fleet.fixture.car.CarRequestFixture;
import br.com.cezarcruz.fleet.core.model.CarModel;
import br.com.cezarcruz.fleet.core.usecase.CreateCarUseCase;
import br.com.cezarcruz.fleet.utils.JsonUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class CreateCarControllerIntegrationTest {

  private MockMvc mockMvc;

  @Mock
  private CreateCarUseCase createCarUseCase;

  @Spy
  private final CarValidator carValidator = new CarValidator();

  @Spy
  private final CarMapper carMapper = new CarMapperImpl();

  @InjectMocks
  private CreateCarController createCarController;

  @BeforeEach
  public void setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(createCarController)
        .setControllerAdvice(new ErrorHandler())
        .build();
  }

  @Test
  @DisplayName("deve validar todos os inputs enviados para o endpoint /v1/car")
  void shouldTestInvalidInput() throws Exception {

    final CarRequest carRequest = CarRequestFixture.getEmpty();

    this.mockMvc.perform(
        post("/v1/car")
            .content(JsonUtils.jsonFrom(carRequest))
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
    final CarRequest carRequest = CarRequestFixture.getCarWithoutMileage();

    this.mockMvc.perform(
        post("/v1/car")
            .content(JsonUtils.jsonFrom(carRequest))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .characterEncoding("utf-8")
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

    verify(carMapper, times(0)).from(any(CarModel.class));
    verify(carMapper, times(0)).from(any(CarRequest.class));
    verify(carValidator, times(1)).validate(any(CarRequest.class));
  }

  @Test
  @DisplayName("deve aceitar todos os campos validos")
  void shouldAcceptValidRequest() throws Exception {

    final CarRequest carRequest = CarRequestFixture.getValidCarRequest();

    when(createCarUseCase.create(any(CarModel.class)))
        .thenAnswer(a -> a.getArgument(0));

    this.mockMvc.perform(post("/v1/car")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(JsonUtils.jsonFrom(carRequest))
        .characterEncoding("utf-8")
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
