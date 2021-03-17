package br.com.cezarcruz.fleet.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Profile("!aws")
@Configuration
@AllArgsConstructor
public class SwaggerConfig {

  private final BuildProperties buildProperties;

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Fleet Manager")
        .description("Manage your fleet")
        .version(buildProperties.getVersion())
        .build();
  }

}
