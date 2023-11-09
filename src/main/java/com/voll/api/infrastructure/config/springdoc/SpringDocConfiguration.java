package com.voll.api.infrastructure.config.springdoc;

// IMPORTS.
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * CONFIGURATION CLASS FOR CUSTOMIZING THE OPEN API DOCUMENTATION USING SPRING DOC.
 * This class defines a custom OpenAPI configuration, including security schemes,
 * for the Swagger UI documentation.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
@Configuration
public class SpringDocConfiguration {

    /**
     * BEAN DEFINITION FOR CUSTOMIZING THE OPEN API SPECIFICATION.
     * Configures a bearer token security scheme for authentication.
     *
     * @return Customized OpenAPI object with the defined security scheme.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }

    /**
     * DUMMY METHOD USED FOR TESTING PURPOSES.
     * Prints a message indicating that the bearer token authentication is working.
     */
    @Bean
    public void message() { System.out.println("bearer is working"); }
}
