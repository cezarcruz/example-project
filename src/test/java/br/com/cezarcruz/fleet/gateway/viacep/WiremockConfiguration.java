package br.com.cezarcruz.fleet.gateway.viacep;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@TestConfiguration
@Profile("wiremock")
public class WiremockConfiguration {

  @Autowired
  private WireMockServer wireMockServer;

  @Bean(initMethod = "start", destroyMethod = "stop")
  public WireMockServer mockServer() {
    return new WireMockServer(9005);
  }

}
