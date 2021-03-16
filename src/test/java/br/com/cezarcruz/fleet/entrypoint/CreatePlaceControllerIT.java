package br.com.cezarcruz.fleet.entrypoint;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.cezarcruz.fleet.entrypoint.mapper.AddressMapperImpl;
import br.com.cezarcruz.fleet.entrypoint.mapper.PlaceMapperImpl;
import br.com.cezarcruz.fleet.entrypoint.request.AddressRequest;
import br.com.cezarcruz.fleet.entrypoint.request.CreatePlaceRequest;
import br.com.cezarcruz.fleet.entrypoint.validator.AddressValidator;
import br.com.cezarcruz.fleet.entrypoint.validator.PlaceValidator;
import br.com.cezarcruz.fleet.model.PlaceModel;
import br.com.cezarcruz.fleet.usecase.CreatePlaceUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CreatePlaceController.class)
class CreatePlaceControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @SpyBean
  private PlaceMapperImpl placeMapper;

  @SpyBean
  private PlaceValidator placeValidator;

  @SpyBean
  private AddressMapperImpl addressMapper;

  @SpyBean
  private AddressValidator addressValidator;

  @MockBean
  private CreatePlaceUseCase createPlaceUseCase;

  @Test
  @DisplayName("deve aceitar a requisicao para criar um novo local")
  void shouldAcceptValidInput() throws Exception {

    final AddressRequest address =
        AddressRequest.builder()
            .cep("13188023")
            .complement("Somewhere in time")
            .number("123A")
            .build();

    final CreatePlaceRequest placeRequest =
        CreatePlaceRequest.builder()
            .description("just another place")
            .address(address)
            .build();

    when(createPlaceUseCase.execute(any())).thenAnswer(a -> ((PlaceModel) a.getArgument(0)).toBuilder().id(1L).build());

    this.mockMvc.perform(post("/v1/place")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(objectMapper.writeValueAsString(placeRequest))
    ).andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.description").value("just another place"))
    ;

  }

  @Test
  void shouldRejectInvalidFields() throws Exception {
    this.mockMvc.perform(post("/v1/place")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(objectMapper.writeValueAsString(CreatePlaceRequest.builder().build()))
    ).andDo(print())
        .andExpect(status().isBadRequest())
    ;
  }

}
