package org.yassir.itlens.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST Survey IT")
                        .description("Documentation de l'API pour l'application de sondages IT.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Support ITLens")
                                .email("support@itlens.com")
                        )
                );
    }
}
