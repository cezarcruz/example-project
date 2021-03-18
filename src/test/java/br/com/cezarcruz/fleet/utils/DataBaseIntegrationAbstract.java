package br.com.cezarcruz.fleet.utils;

import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;

@DataJpaTest
@ComponentScan("br.com.cezarcruz.fleet.gateway.dataprovider")
public class  DataBaseIntegrationAbstract extends SpringIntegrationTest {

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
