package br.com.cezarcruz.fleet.utils;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

@AutoConfigureWireMock(port = 0)
@SpringBootTest
public class WiremockIntegrationAbstract extends SpringIntegrationTest {

}
