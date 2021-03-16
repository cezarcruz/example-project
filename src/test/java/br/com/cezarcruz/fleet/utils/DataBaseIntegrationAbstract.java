package br.com.cezarcruz.fleet.utils;

import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(properties = "spring.main.lazy-initialization=true")
public class DataBaseIntegrationAbstract extends SpringIntegrationTest {

  @Autowired
  private Set<JpaRepository<?, ?>> repos;

  @BeforeEach
  public void resetState() {
    cleanAllDatabases();
  }

  private void cleanAllDatabases() {
    repos.forEach(JpaRepository::deleteAllInBatch);
  }

}
