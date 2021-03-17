package br.com.cezarcruz.fleet.entrypoint;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.cezarcruz.fleet.entrypoint.mapper.AddressMapperImpl;
import br.com.cezarcruz.fleet.entrypoint.mapper.PlaceMapper;
import br.com.cezarcruz.fleet.entrypoint.mapper.PlaceMapperImpl;
import br.com.cezarcruz.fleet.entrypoint.request.AddressRequest;
import br.com.cezarcruz.fleet.entrypoint.request.CreatePlaceRequest;
import br.com.cezarcruz.fleet.entrypoint.validator.AddressValidator;
import br.com.cezarcruz.fleet.entrypoint.validator.PlaceValidator;
import br.com.cezarcruz.fleet.fixture.place.AddressRequestFixture;
import br.com.cezarcruz.fleet.fixture.place.PlaceRequestFixture;
import br.com.cezarcruz.fleet.core.model.PlaceModel;
import br.com.cezarcruz.fleet.core.usecase.CreatePlaceUseCase;
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
class CreatePlaceControllerIntegrationTest {

  private MockMvc mockMvc;

  @InjectMocks
  private CreatePlaceController controller;

  @Spy
  private final PlaceMapper placeMapper = new PlaceMapperImpl(new AddressMapperImpl());

  @Spy
  private final PlaceValidator placeValidator = new PlaceValidator(new AddressValidator());

  @Mock
  private CreatePlaceUseCase createPlaceUseCase;

  @BeforeEach
  public void setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(controller)
        .setControllerAdvice(new ErrorHandler())
        .build();
  }

  @Test
  @DisplayName("deve aceitar a requisicao para criar um novo local")
  void shouldAcceptValidInput() throws Exception {

    final AddressRequest address = AddressRequestFixture.getValidAddress();
    final CreatePlaceRequest placeRequest = PlaceRequestFixture.getPlaceWithAddress(address);

    when(createPlaceUseCase.execute(any()))
        .thenAnswer(a -> ((PlaceModel) a.getArgument(0)).toBuilder().id(1L).build());

    this.mockMvc.perform(post("/v1/place")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(JsonUtils.jsonFrom(placeRequest))
    ).andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.description").value("just another place"))
    ;

  }

  @Test
  void shouldRejectInvalidFields() throws Exception {
    this.mockMvc.perform(post("/v1/place")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(JsonUtils.jsonFrom(PlaceRequestFixture.getEmpty()))
    ).andDo(print())
        .andExpect(status().isBadRequest())
    ;
  }

}
