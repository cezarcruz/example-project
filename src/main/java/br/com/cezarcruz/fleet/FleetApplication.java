package br.com.cezarcruz.fleet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class FleetApplication {

	public static void main(final String...args) {
		SpringApplication.run(FleetApplication.class, args);
	}

}
