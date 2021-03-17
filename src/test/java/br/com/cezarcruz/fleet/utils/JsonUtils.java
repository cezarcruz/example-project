package br.com.cezarcruz.fleet.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class JsonUtils {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  public static String jsonFrom(final Object object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (final JsonProcessingException e) {

      log.error("cant deserialize: {}", object);

      return StringUtils.EMPTY;
    }
  }

}
