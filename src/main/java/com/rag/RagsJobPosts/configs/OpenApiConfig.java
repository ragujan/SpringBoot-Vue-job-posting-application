package com.rag.RagsJobPosts.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    private final OpenApiProperties properties;
    final String securitySchemeName = "bearerAuth";

    public OpenApiConfig(OpenApiProperties properties) {
        this.properties = properties;
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components().addSecuritySchemes(securitySchemeName,
                        new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .info(new Info()
                        .title(properties.getTitle())
                        .version(properties.getVersion())
                        .contact(new Contact()
                                .email(properties.getContactEmail())
                                .name(properties.getOrganization())
                                .url(properties.getWebsite()))
                        .license(new License()
                                .name(properties.getLicense().getType())
                                .url(properties.getLicense().getTermsOfService())))
                .servers(List.of(
                        new Server().url(properties.getLocalUrl()),
                        new Server().url(properties.getUatUrl()),
                        new Server().url(properties.getDevUrl()),
                        new Server().url(properties.getProdUrl())
                ));
    }
}
