package com.raminagrobis.centraleachat.app.swagger

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun openApi() : OpenAPI{
        return OpenAPI()
            .info(Info()
                .title("Raminagrobis API Docs")
                .description("Raminagrobis REST API documentation")
                .version("1.0.0"))
    }
}