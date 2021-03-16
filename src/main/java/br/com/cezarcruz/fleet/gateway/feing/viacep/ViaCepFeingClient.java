package br.com.cezarcruz.fleet.gateway.feing.viacep;

import br.com.cezarcruz.fleet.gateway.json.ViaCepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://viacep.com.br/ws/", name = "via-cep")
public interface ViaCepFeingClient {

  @GetMapping("/{cep}/json/")
  ViaCepResponse getBy(@PathVariable("cep") final String cep);

}
