package com.rag.RagsJobPosts.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "tms.openapi")
@Data
public class OpenApiProperties {

    private String localUrl;
    private String devUrl;
    private String uatUrl;
    private String prodUrl;
    private String contactEmail;
    private String organization;
    private String website;
    private String title;
    private String version;
    private License license = new License();

    @Data
    public static class License {
        private String type;
        private String description;
        private String termsOfService;
    }
}
