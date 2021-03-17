package br.com.cezarcruz.fleet.gateway.feign.viacep;

import br.com.cezarcruz.fleet.gateway.json.ViaCepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${viacep.url}", name = "via-cep")
public interface ViaCepFeignClient {

  @GetMapping(value = "/{cep}/json/", consumes = MediaType.APPLICATION_JSON_VALUE)
  ViaCepResponse getBy(@PathVariable("cep") final String cep);

}
