package bank.foundation.avitepa.Avitepa_Bank;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class AvitepaBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvitepaBankApplication.class, args);
	}
	
	@Bean
	  public GroupedOpenApi adminApi() {
	      return GroupedOpenApi.builder()
	              .group("springshop-admin")
	              .pathsToMatch("/api/**")
	              .build();
	  }
}
