package tn.climora.stationssources.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI climoraOpenApi() {
        return new OpenAPI().info(new Info()
                .title("Climora MS2 - Stations & sources")
                .description("Catalogue des sources de donnees meteo et de leurs stations de mesure")
                .version("1.0.0"));
    }
}
