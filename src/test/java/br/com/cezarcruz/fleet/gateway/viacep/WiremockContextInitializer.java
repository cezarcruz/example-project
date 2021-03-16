package br.com.cezarcruz.fleet.gateway.viacep;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;

public class WiremockContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

  @Override
  public void initialize(final ConfigurableApplicationContext applicationContext) {
    final WireMockServer wireMockServer =
        new WireMockServer(new WireMockConfiguration().dynamicPort());

    wireMockServer.start();

    applicationContext.getBeanFactory().registerSingleton("wireMock", wireMockServer);

    applicationContext.addApplicationListener(event -> {
      if (event instanceof ContextClosedEvent) {
        wireMockServer.stop();
      }
    });
  }

}
