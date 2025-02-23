package br.viniciusjomori.SpringTest.App;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
 
    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Spring Test OpenAPI")
                .version("v1")
                .description("Studying OpenAPI")
                .termsOfService("https://github.com/viniciusjomori")
                .license(
                    new License()
                        .name("Apache 2.0")
                        .url("https://github.com/viniciusjomori")));
    }
}
