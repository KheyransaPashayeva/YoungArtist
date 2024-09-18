package az.atl.youngartist.configuration;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "Authorization",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Contact contact = new Contact();
        contact.setEmail("admin@gmail.com");
        contact.setName("123456");

        Info info = new Info().
                title("admin").
                version("1.0").
                contact(contact).
                description("This Api exposes endpoints to manage tutorials.");
        return new OpenAPI()
                .info(info)
                .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList("Authorization"));
    }
}
