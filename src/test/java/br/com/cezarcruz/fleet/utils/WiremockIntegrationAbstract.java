package br.com.cezarcruz.fleet.utils;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

@AutoConfigureWireMock(port = 9005)
@SpringBootTest(properties = "spring.main.lazy-initialization=true")
public class WiremockIntegrationAbstract extends SpringIntegrationTest {

}
