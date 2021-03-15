package br.com.cezarcruz.fleet;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DirtiesContext
@SpringBootTest(classes = FleetApplication.class)
public abstract class SpringBootContextAbstract {

}
