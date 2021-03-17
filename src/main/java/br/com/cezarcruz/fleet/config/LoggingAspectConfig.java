package br.com.cezarcruz.fleet.config;

import br.com.cezarcruz.fleet.aop.LoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class LoggingAspectConfig {

  @Bean
  public LoggingAspect loggingAspect() {
    return new LoggingAspect();
  }

}
