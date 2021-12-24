package br.com.leroymerlin.estoque;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jms.annotation.EnableJms;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@EnableJms
@EnableJpaAuditing
@SpringBootApplication
public class EstoqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstoqueApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
		var license = new License().name("Apache 2.0").url("http://springdoc.org");

		var info = new Info()
				.title("Estoque - API")
				.version(appVersion)
				.description("Gerenciamento de Produtos")
				.termsOfService("http://swagger.io/terms/")
				.license(license);

		return new OpenAPI().info(info);
	}

}
