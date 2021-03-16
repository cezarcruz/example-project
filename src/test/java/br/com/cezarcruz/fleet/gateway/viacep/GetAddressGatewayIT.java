package br.com.cezarcruz.fleet.gateway.viacep;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = WiremockContextInitializer.class)
@AutoConfigureWebTestClient
class GetAddressGatewayIT {

  @Autowired
  private WireMockServer wireMockServer;

  @AfterEach
  void afterEach() {
    wireMockServer.resetAll();
  }

  @Test
  void shouldDoSomething() {

  }

}
