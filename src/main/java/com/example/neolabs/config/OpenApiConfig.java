package com.example.neolabs.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Neolabs CMS API documentation",
        description = """
                Enums spreadsheet : <a href=\"https://docs.google.com/spreadsheets/d/1yNkGDCksmzdz5fpL_rMUeM4tGkX-iiVTZsaZ2LVW7ws/edit?usp=sharing\">Link</a>
                
                WebSocket documentation : <a href=\"https://docs.google.com/document/d/1LQ7t_IUqDAp-9BAFAbp9FOdzHC0t0xKo_DhsRkKrSeI/edit?usp=sharing\">Link</a>
                """,
        contact = @Contact(
                name = "Dwayne Johnson",
                email = "motivation@rock.scala"
        ),
        version = "v3",
        license = @License(
                name = "TrustMeBro Licence",
                url = "https://github.com/thombergs/code-examples/blob/master/LICENSE"))
)
public class OpenApiConfig {

    @Bean
    public OpenAPI customizeOpenAPI() {
        final String securitySchemeName = "Bearer Authorization";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .description(
                                        "Provide a JWT token. JWT token can be obtained from logging in through this API.")
                                .bearerFormat("JWT")));
    }
}
